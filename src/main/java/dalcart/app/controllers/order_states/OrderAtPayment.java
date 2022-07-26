package dalcart.app.controllers.order_states;

import dalcart.app.models.Factories.IProductPersistenceFactory;
import dalcart.app.models.Factories.ProductPersistenceFactory;
import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.Repository.OrderProductsDB;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;

import java.util.Map;

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
        //IProductModel[] products = orderProductsDB.getProductByOrderId(order.getOrderId());
        Map<IProductModel,Integer> products_and_quantity =  orderProductsDB.getProductsOfOrder(order.getOrderId());

        //System.out.println("Products:::::" + products.length);
        for (IProductModel product : products_and_quantity.keySet()){
            if(product.getProductQuantity()-products_and_quantity.get(product) >= 0 ) {
                product.updateProduct(product.getProductId(), product.getProductQuantity() - products_and_quantity.get(product), product.getEnabled(), productDB);
            }else{
                return false;
            }
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
