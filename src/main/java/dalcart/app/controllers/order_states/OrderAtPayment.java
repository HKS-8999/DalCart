package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;

public class OrderAtPayment implements OrderState {

    public OrderAtPayment(){
    }
    @Override
    public boolean completeState(IOrderModel order) {
        //checkhere if the payment details are valid
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
