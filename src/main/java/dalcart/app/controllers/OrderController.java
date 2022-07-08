package dalcart.app.controllers;

import dalcart.app.Repository.OrderProducts;
import dalcart.app.items.IProduct;
import dalcart.app.items.IUser;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.OrderModel;

public class OrderController {
    public void placeOrder(IUser user, IProduct[] products){
        IOrderModel order = new OrderModel();
        order.setUserId(user.getUserID());
        Integer savedOrderId = order.save();
        if(savedOrderId != null){
            //attach product ids with Orders in order_products table
            for(IProduct product: products){
                OrderProducts.saveOrderProduct(savedOrderId,product.getProductId());
            }
        }
    }
}
