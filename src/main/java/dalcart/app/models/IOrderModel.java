package dalcart.app.models;


import dalcart.app.controllers.order_states.OrderState;

public interface IOrderModel extends Model {
    Integer getOrderId();

    void setOrderId(Integer orderId);

    String getOrderNumber();

    String getCreatedAt();

    String getUpdatedAt();

    Integer getUserId();

    void setUserId(Integer userId);

    OrderState getState();

    void setState(OrderState state);
}
