package dalcart.app.controllers;

import dalcart.app.Repository.OrderProducts;
import dalcart.app.Repository.ConnectionManager;

import dalcart.app.items.OrderAtCart;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.OrderModel;
import dalcart.app.models.User;
import dalcart.app.models.*;

import java.sql.SQLException;

public class OrderController {
    public void addToOrder(User user, IProductModel[] products) throws SQLException {
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
            for (IProductModel product : products) {
                System.out.println("Product ID:" + product.getProductId());
                product.updateProduct(product.getProductId(),product.getProductQuantity()-1,product.getEnabled());
                OrderProducts.saveOrderProduct(orderId, product.getProductId());
            }
        }
        connectionManager.commit();
    }
}
