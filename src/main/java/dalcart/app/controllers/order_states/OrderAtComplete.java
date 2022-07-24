package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;
import dalcart.app.utils.OrderUtils;

public class OrderAtComplete implements OrderState{

    @Override
    public void completeState(IOrderModel order) {
        //no need to update state as this is the final state
        order.setState(this.getNextState());
        order.save();
    }

    @Override
    public OrderState getNextState() {
        return null;
    }

    @Override
    public OrderState getCurrentState() {
        return this;
    }

    @Override
    public String getStateName() {
        return "complete";
    }
}
