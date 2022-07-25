package dalcart.app.controllers.order_states;

import dalcart.app.models.IOrderModel;

public interface OrderState {
    public boolean completeState(IOrderModel order);
    public OrderState getNextState();
    public OrderState getCurrentState();
    public String getStateName();
    public boolean isComplete();
}
