package dalcart.app.models;

public interface IOrderModel extends Model {
    enum OrderStates{
        CART,
        ADDRESS,
        PAYMENT,
        COMPLETE
    }
    public void setOrderId(Integer orderId);
    public void setUserId(Integer userId);
    public Integer getOrderId();
    public String getOrderNumber();
    public String getCreatedAt();
    public String getUpdatedAt();
    public Integer getUserId();
    public void setState(OrderStates state);
    public OrderStates getState();
}
