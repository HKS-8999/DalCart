package dalcart.app.controllers;

import dalcart.app.Repository.OrderProducts;
import dalcart.app.database.ConnectionManager;
import dalcart.app.items.IProduct;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IUser;
import dalcart.app.models.OrderModel;
import dalcart.app.models.User;

import java.sql.SQLException;

public class OrderController {
    public void addToOrder(User user, IProduct[] products) throws SQLException {
        //if order is not already there for the user it creates it
        IOrderModel order;
        IOrderModel existingOrder = OrderModel.getOrderByUserId(user.getUserID());
        Integer orderId;
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        if(existingOrder == null)
        {
            order = new OrderModel();
            order.setUserId(user.getUserID());
            orderId = order.save();
        }else{
            order = existingOrder;
            orderId = existingOrder.getOrderId();
        }

        if (orderId != null) {
            //attach product ids with Orders in order_products table
            for (IProduct product : products) {
                OrderProducts.saveOrderProduct(orderId, product.getProductId());
            }
        }
        connectionManager.commit();
    }
}
