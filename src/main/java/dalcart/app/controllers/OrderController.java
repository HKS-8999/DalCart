package dalcart.app.controllers;

import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.OrderProductsDB;
import dalcart.app.Repository.ConnectionManager;

import dalcart.app.Repository.UserDB;
import dalcart.app.controllers.order_states.OrderAtCart;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.OrderModel;
import dalcart.app.models.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@RequestMapping(value = {"/order"})
@Component
public class OrderController
{
//    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
//    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
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
    public boolean submitOrder() throws SQLException{
        //process the order at cart stage
        //process the order at address stage
        //process the order at payment stage
        UserDB userdb = new UserDB();
        IUser user = userdb.loadUserAttributesByUserId(1);
        IOrderModel currentOrder = OrderModel.getOrderByUserId(user.getUserID());

        while(currentOrder.getState().isComplete() == false){
            System.out.println("State:" + currentOrder.getState().getStateName());
            if(currentOrder.getState().completeState(currentOrder) == false){
                return false;
            }
        }
        System.out.println("All Order States Passed. Order is Placed Now");
        return true;
    }
}
