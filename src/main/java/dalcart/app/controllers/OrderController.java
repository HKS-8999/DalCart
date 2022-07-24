package dalcart.app.controllers;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.OrderProducts;
import dalcart.app.Repository.ConnectionManager;

import dalcart.app.items.OrderAtCart;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.OrderModel;
import dalcart.app.models.User;
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
                OrderProducts.saveOrderProduct(orderId, product.getProductId());
            }
        }
        connectionManager.commit();
    }

    @PostMapping("/submit_order")
    @ResponseBody
    public String submitOrder() throws SQLException{
        return "success";
    }
}
