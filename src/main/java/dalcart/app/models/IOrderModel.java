package dalcart.app.models;

import dalcart.app.items.OrderState;

public interface IOrderModel extends Model {
    public void setOrderId(Integer orderId);
    public void setUserId(Integer userId);
    public Integer getOrderId();
    public String getOrderNumber();
    public String getCreatedAt();
    public String getUpdatedAt();
    public Integer getUserId();
    public void setState(OrderState state);
    public OrderState getState();
}
