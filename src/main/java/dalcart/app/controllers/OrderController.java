package dalcart.app.controllers;

import dalcart.app.Repository.OrderProducts;
import dalcart.app.items.IProduct;
import dalcart.app.items.IUser;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.OrderModel;

public class OrderController {
    public void addToOrderOrder(IUser user, IProduct[] products){
        //if order is not already there for the user it creates it
        IOrderModel order;
        IOrderModel existingOrder = OrderModel.getOrderByUserId(user.getUserID());
        Integer orderId;
        if(existingOrder == null) {
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

    }
}
