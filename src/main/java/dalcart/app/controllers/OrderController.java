package dalcart.app.controllers;

import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.*;

import dalcart.app.controllers.order_states.OrderAtCart;
import dalcart.app.controllers.order_states.OrderAtComplete;
import dalcart.app.controllers.order_states.OrderAtPayment;
import dalcart.app.controllers.order_states.OrderState;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.OrderModel;
import dalcart.app.models.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping(value = {"/order"})
@Component
public class OrderController
{
    public void addToOrder(IUser user, IProductModel[] products) throws SQLException
    {
        IOrderModel order;
        IOrderModel existingOrder = OrderModel.getOrderByUserId(user.getUserID());
        System.out.println("User Id:" + user.getUserID());
        Integer orderId;
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        if(existingOrder == null)
        {
            System.out.println("Creating new order");
            order = new OrderModel();
            order.setUserId(user.getUserID());
            order.setState(new OrderAtCart());
            orderId = order.save();
        }else{
            order = existingOrder;
            orderId = existingOrder.getOrderId();
        }

        if (orderId != null) {
            System.out.println("Order ID: " + orderId);
            IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
            IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
            for (IProductModel product : products) {
                System.out.println("Product ID:" + product.getProductId());
                //product.updateProduct(product.getProductId(),product.getProductQuantity()-1,product.getEnabled(), productDB);
                OrderProductsDB.saveOrderProduct(orderId, product.getProductId());
            }
        }
        connectionManager.commit();
    }

    @PostMapping("/submit_order")
    @ResponseBody
    public String submitOrder(@RequestParam Map<String,String> allParams, ModelAndView model, HttpSession session, SessionService sessionService, RedirectAttributes atts) throws SQLException{
        //process the order at cart stage
        //process the order at address stage
        //process the order at payment stage
        System.out.println("Inside.....");
        UserDB userdb = new UserDB();
        OrderState orderAtPayment = new OrderAtPayment();
        OrderState orderComplete = new OrderAtComplete();
        IUser user = userdb.loadUserAttributesByUserId(1);
        IOrderModel currentOrder = OrderModel.getOrderByUserId(user.getUserID());

        Integer userId = (Integer) session.getAttribute("user");
        OrderDB db = new OrderDB();
        IOrderModel order = db.findOrderInCartByUserId(userId);
        Integer orderId =order.getOrderId();
        System.out.println(orderId);

        CheckoutController checkoutController = new CheckoutController();
        db.changeStateOfOrder("address", orderId);
        if(checkoutController.validateAddress(allParams, model, session, sessionService, atts))
        {
            currentOrder.setState(orderAtPayment);
            db.changeStateOfOrder("payment", orderId);
            if(checkoutController.validatePaymentAndPlaceOrder(allParams, model, session, sessionService, atts))
            {
                currentOrder.setState(orderComplete);
                db.changeStateOfOrder("complete", orderId);
                return "Success";
            }
            else
            {
                return "PaymentFailure";
            }
        }
        else
        {
            return "AddressFailure";
        }
    }
}
