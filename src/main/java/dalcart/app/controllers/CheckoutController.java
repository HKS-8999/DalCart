package dalcart.app.controllers;

import dalcart.app.models.*;
import dalcart.app.models.Factories.*;
import dalcart.app.models.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckoutController {
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductModel productModel = productModelFactory.createProductModel();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    OrderProductsDB o = new OrderProductsDB();
    OrderDB db = new OrderDB();

    @GetMapping("/cart")
    public ModelAndView listgetproducts(ModelAndView model, HttpSession session, SessionService sessionService) {
        if (sessionService.isUserInSession(session) == false || SessionService.isSessionValid(session) == false) {
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
            return modelAndView;
        }
        HashMap<Integer, Integer> productIds = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("user");
        OrderProductsDB o = new OrderProductsDB();
        productIds = o.getProductsOfUser(userId);
        if (productIds == null) {
            model.addObject("message", "Nothing in the cart");
        } else {
            ArrayList<IProductModel> allProducts = new ArrayList<>();
            for (Map.Entry<Integer, Integer> val : productIds.entrySet()) {
                Integer id = val.getKey();
                allProducts.add(productModel.getProductById(id, productDB));
            }
            if (allProducts.size() == 0) {
                model.addObject("message", "Nothing in the cart");
            }
            Integer total = productModel.getTotalOfProducts(productDB, productIds);
            model.addObject("products", allProducts);
            model.addObject("quantity", productIds);
            model.addObject("total", total);
        }
        model.setViewName("checkout");
        return model;
    }

    @PostMapping(value = "/increaseQuantityOfProduct")
    public ModelAndView increaseProductQuantity(@RequestParam("id") int id, @RequestParam("quantity") int quantity,
                                                ModelAndView model, HttpSession session, SessionService sessionService) {
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId = order.getOrderId();
        Boolean b = o.increaseProductQuantity(id, quantity, orderId);
        return model;
    }

    @PostMapping("/decreaseQuantityOfProduct")
    public ModelAndView decreaseProductQuantity(@RequestParam("id") int id, @RequestParam("quantity") int quantity, ModelAndView model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId = order.getOrderId();
        o.decreaseProductQuantity(id, quantity, orderId);
        return model;
    }

    @PostMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam("id") int id, ModelAndView model, HttpSession session) {
        try {
            Integer userId = (Integer) session.getAttribute("user");
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId = order.getOrderId();
            OrderDB.removeProductFromCart(orderId, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @PostMapping("/validateAddress")
    public boolean validateAddress(@RequestParam Map<String, String> allParams, HttpSession session) {
        try {
            IDeliveryAddressPersistenceFactory deliveryInformationPersistenceFactory = new DeliveryInformationAddressPersistenceFactory();
            IDeliveryInformationPersistence deliveryInformationPersistence = deliveryInformationPersistenceFactory.createIDeliveryInformationPersistence();
            String name = allParams.get("fullname");
            String email = allParams.get("email");
            String address = allParams.get("address");
            String mobileNumber = allParams.get("phone");
            Integer userId = (Integer) session.getAttribute("user");
            OrderDB db = new OrderDB();
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId = order.getOrderId();
            IDeliveryInformationModelFactory deliveryInformationModelFactory = new DeliveryInformationModelFactory();
            DeliveryInformationModel deliveryInformationModel = deliveryInformationModelFactory.createDeliveryInformation(name, email, address, mobileNumber);
            DeliveryInformationValidator validator = new DeliveryInformationValidator();
            if (validator.validateDeliveryDetails(deliveryInformationModel)) {
                deliveryInformationModel.addDeliveryAddress(deliveryInformationModel, deliveryInformationPersistence, orderId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/validatePaymentInfo")
    public boolean validatePaymentAndPlaceOrder(@RequestParam Map<String, String> allParams, HttpSession session) {
        try {
            IPaymentPersistenceFactory iPaymentPersistenceFactory = new PaymentPersistenceFactory();
            IPaymentPersistence iPaymentPersistence = iPaymentPersistenceFactory.createIPaymentPersistence();
            String cardNumber = allParams.get("cardnumber");
            String cardCVV = allParams.get("cvv");
            String expiryDate = allParams.get("expiry");
            Integer userId = (Integer) session.getAttribute("user");
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId = order.getOrderId();
            HashMap<Integer, Integer> productIds = o.getProductsOfUser(userId);
            Integer total = productModel.getTotalOfProducts(productDB, productIds);
            IPaymentModelFactory paymentModelFactory = new PaymentModelFactory();
            PaymentModel paymentModel = paymentModelFactory.createPaymentModel(cardNumber, cardCVV, expiryDate);
            PaymentInformationValidator validator = new PaymentInformationValidator();
            if (validator.validatePaymentInformation(paymentModel)) {
                paymentModel.savePaymentInformation(iPaymentPersistence, orderId, userId, total);
                productModel.decreaseProductQuantity(productDB, productIds);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
