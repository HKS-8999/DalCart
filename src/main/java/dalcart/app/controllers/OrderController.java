package dalcart.app.controllers;

import dalcart.app.models.Factories.IProductPersistenceFactory;
import dalcart.app.models.Factories.ProductPersistenceFactory;
import dalcart.app.models.Repository.ConnectionManager;
import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.Repository.OrderProductsDB;
import dalcart.app.controllers.order_states.OrderAtCart;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.IUser;
import dalcart.app.models.OrderModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    public void addToOrder(IUser user, IProductModel[] products) throws SQLException {
        IOrderModel order;
        IOrderModel existingOrder = OrderModel.getOrderByUserId(user.getUserID());
        Integer orderId;
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        if (existingOrder == null) {
            order = new OrderModel();
            order.setUserId(user.getUserID());
            order.setState(new OrderAtCart());
            orderId = order.save();
        } else {
            order = existingOrder;
            orderId = existingOrder.getOrderId();
        }
        if (orderId != null) {
            IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
            IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
            for (IProductModel product : products) {
                OrderProductsDB.saveOrderProduct(orderId, product.getProductId());
            }
        }
        connectionManager.commit();
    }

    @PostMapping("/submit_order")
    public String submitOrder(@RequestParam Map<String, String> allParams, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user");
        IOrderModel currentOrder = OrderModel.getOrderByUserId(userId);
        CheckoutController checkoutController = new CheckoutController();
        boolean addressAndPaymentcheck = checkoutController.validateAddress(allParams, session)
                && checkoutController.validatePaymentAndPlaceOrder(allParams, session);
        if (addressAndPaymentcheck) {
            while (currentOrder.getState().isComplete() == false) {
                if (currentOrder.getState().completeState(currentOrder) == false) {
                    return "failure";
                }
            }
        }
        return "Success";
    }
}