package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;

public class OrderAtComplete implements OrderState{

    @Override
    public boolean completeState(IOrderModel order) {
        //no need to update state as this is the final state
        //order.setState(this.getNextState());
        //order.save();
        return true;
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

    public boolean isComplete(){
        return true;
    }
}
