package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;

public class OrderAtAddress implements OrderState{
    @Override
    public boolean completeState(IOrderModel order) {
        //checkhere if the order address is invalid


        order.setState(this.getNextState());
        order.save();
        return true;
    }

    @Override
    public OrderState getNextState() {
        return (new OrderAtPayment());
    }

    @Override
    public OrderState getCurrentState() {
        return null;
    }

    @Override
    public String getStateName() {
        return "address";
    }

    public boolean isComplete(){
        return false;
    }
}
