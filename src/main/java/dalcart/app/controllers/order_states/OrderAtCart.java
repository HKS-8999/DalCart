package dalcart.app.controllers.order_states;

import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.Repository.OrderProductsDB;
import dalcart.app.models.Repository.ProductDB;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;

import java.util.List;

public class OrderAtCart implements OrderState{

    @Override
    public boolean completeState(IOrderModel order) {
        //check if cart items are not out of stock.
        List<Integer> productIds = OrderProductsDB.getProductIdsByOrderId(order.getOrderId());
        for(Integer productId: productIds){
            IProductModel iProductModel = new ProductModel();
            IProductPersistence iProductPersistence = new ProductDB();
            IProductModel product = iProductModel.getProductById(productId,iProductPersistence);
            if(product.getProductQuantity() <= 0){
                return false;
            }
        }
        order.setState(getNextState());
        order.save();
        return true;
    }

    @Override
    public OrderState getNextState() {
        return (new OrderAtAddress());
    }

    @Override
    public OrderState getCurrentState() {
        return this;
    }

    @Override
    public String getStateName() {
        return "cart";
    }

    public boolean isComplete(){
        return false;
    }
}
