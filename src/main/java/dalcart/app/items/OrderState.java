package dalcart.app.items;

import dalcart.app.models.IOrderModel;

public interface OrderState {
    public void completeState(IOrderModel order);
    public OrderState getNextState();
    public OrderState getCurrentState();
    public String getStateName();
}
