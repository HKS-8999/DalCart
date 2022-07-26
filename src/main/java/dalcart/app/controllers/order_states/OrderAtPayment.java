package dalcart.app.controllers.order_states;

import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.OrderProductsDB;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;

public class OrderAtPayment implements OrderState {

    public OrderAtPayment(){
    }
    @Override
    public boolean completeState(IOrderModel order) {
        //checkhere if the payment details are valid
        //if payment is successful
        IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
        IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();
        OrderProductsDB orderProductsDB = new OrderProductsDB() ;
        System.out.println("Order Id:::::" + order.getOrderId());
        IProductModel[] products = orderProductsDB.getProductByOrderId(order.getOrderId());
        System.out.println("Products:::::" + products.length);
        for (IProductModel product : products) {
            System.out.println("Product ID:" + product.getProductId());
            System.out.println(product.getProductId() + "::::" + (product.getProductQuantity()-1) + "::::" + product.getEnabled());
            product.updateProduct(product.getProductId(),product.getProductQuantity()-1,product.getEnabled(), productDB);
        }
        order.setState(this.getNextState());
        order.save();
        return true;
    }

    @Override
    public OrderState getNextState() {
        return (new OrderAtComplete());
    }

    @Override
    public OrderState getCurrentState() {
        return this;
    }

    @Override
    public String getStateName() {
        return "payment";
    }

    public boolean isComplete(){
        return false;
    }
}
