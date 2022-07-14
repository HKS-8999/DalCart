package dalcart.app.items;

import dalcart.app.models.IOrderModel;
import dalcart.app.utils.OrderUtils;

public class OrderAtPayment implements OrderState{

    public OrderAtPayment(){
    }
    @Override
    public void completeState(IOrderModel order) {
        order.setState(this.getNextState());
        order.save();
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
}
