package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;
import dalcart.app.utils.OrderUtils;

public class OrderAtCart implements OrderState{

    @Override
    public void completeState(IOrderModel order) {
        order.setState(new OrderAtAddress());
        order.save();
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
}
