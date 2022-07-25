package dalcart.app.controllers;

import dalcart.app.Factories.*;
import dalcart.app.Repository.*;
import dalcart.app.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckoutController
{
    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductModel productModel = productModelFactory.createProductModel();
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
    OrderProductsDB o = new OrderProductsDB();
    OrderDB db = new OrderDB();
    @GetMapping("/cart")
    public ModelAndView listgetproducts (ModelAndView model, HttpSession session, SessionService sessionService) throws IOException
    {
        if (sessionService.isUserInSession(session) == false || sessionService.isSessionValid(session) == false)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/logout");
            return modelAndView;
        }

        HashMap<Integer, Integer> productIds = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("user");
        productIds = o.getProductsOfUser(userId);
        ArrayList<IProductModel> allProducts = new ArrayList<>();
        for(Map.Entry<Integer, Integer> val : productIds.entrySet())
        {
            Integer id = val.getKey();
            allProducts.add(productModel.getProductById(id, productDB));
        }
        if(allProducts.size() == 0)
        {
            model.addObject("message", "Nothing in the cart");
        }
        Integer total = productModel.getTotalOfProducts(productDB,productIds);
        model.addObject("products",allProducts);
        model.addObject("quantity",productIds);
        model.addObject("total", total);
        model.setViewName("checkout");
        return model;
    }


    @PostMapping("/increaseQuantityOfProduct")
    public ModelAndView increaseProductQuantity(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService)
    {
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId = order.getOrderId();
        Boolean b = o.increaseProductQuantity(Integer.valueOf(allParams.get("id")), Integer.valueOf(allParams.get("quantity")), orderId);
        System.out.println(Integer.valueOf(allParams.get("id")));
        System.out.println(Integer.valueOf(allParams.get("quantity")));
        return model;
    }

    @PostMapping("/decreaseQuantityOfProduct")
    public ModelAndView decreaseProductQuantity(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService)
    {
        Integer userId = (Integer) session.getAttribute("user");
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId =order.getOrderId();
        System.out.println(orderId);
        System.out.println(Integer.valueOf(allParams.get("id")));
        System.out.println(Integer.valueOf(allParams.get("quantity")));
        Boolean b = o.decreaseProductQuantity(Integer.valueOf(allParams.get("id")),Integer.valueOf(allParams.get("quantity")), orderId);
        return model;
    }

    @PostMapping("/removeFromCart")
    public ModelAndView removeFromCart(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService)
    {
        try
        {
            Integer userId = (Integer) session.getAttribute("user");
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId =order.getOrderId();
            db.removeProductFromCart(orderId,Integer.valueOf(allParams.get("id")));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return model;
    }

    @PostMapping("/validateAndPlaceOrder")
    public ModelAndView validateAndPlaceOrder(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService, RedirectAttributes atts)
    {
        try
        {
            IDeliveryAddressPersistenceFactory deliveryInformationPersistenceFactory = new DeliveryInformationAddressPersistenceFactory();
            IDeliveryInformationPersistence deliveryInformationPersistence =  deliveryInformationPersistenceFactory.createIDeliveryInformationPersistence();
            String name = allParams.get("fullname");
            String email = allParams.get("email");
            String address = allParams.get("address");
            String mobileNumber = allParams.get("phone");
            Integer userId = (Integer) session.getAttribute("user");
            IOrderModel order = db.findOrderInCartByUserId(userId);
            Integer orderId =order.getOrderId();
            IDeliveryInformationModelFactory deliveryInformationModelFactory = new DeliveryInformationModelFactory();
            DeliveryInformationModel deliveryInformationModel = deliveryInformationModelFactory.createDeliveryInformation(name, email, address, mobileNumber);
            DeliveryInformationValidator validations = new DeliveryInformationValidator();
//            deliveryInformationModel.addDeliveryAddress(deliveryInformationModel, deliveryInformationPersistence, orderId);
            if(validations.isFullNameValid(deliveryInformationModel))
            {
                System.out.println("True");
                if(validations.isEmailAddressValid(deliveryInformationModel))
                {
                    System.out.println("True");
                    if(validations.isMobileNumberValid(deliveryInformationModel))
                    {
                        System.out.println("True");
                        if(validations.isAddressValid(deliveryInformationModel))
                        {
                            System.out.println("True");
                            deliveryInformationModel.addDeliveryAddress(deliveryInformationModel, deliveryInformationPersistence, orderId);
                            return new ModelAndView("redirect:/thankyou");
                        }
                        else
                        {
                            atts.addFlashAttribute("invalidAddress","Invalid Address !!");
                            model.addObject("Error",atts);
                            return model;
                        }
                    }
                    else
                    {
                        atts.addFlashAttribute("invalidMobileNumber","Invalid Mobile Number !!");
                        model.addObject("Error",atts);
                        return model;
                    }
                }
                else
                {
                    atts.addFlashAttribute("invalidEmail","Invalid Email Address !!");
                    model.addObject("Error",atts);
                    return model;
                }
            }
            else
            {
                atts.addFlashAttribute("invalidName","Invalid Full Name !!");
                model.addObject("Error",atts);
                return model;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
