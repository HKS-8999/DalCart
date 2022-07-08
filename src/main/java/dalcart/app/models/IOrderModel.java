package dalcart.app.models;

import dalcart.app.items.IProduct;
import dalcart.app.items.IUser;

public interface IOrderModel extends Model {
    enum OrderStates{
        CART,
        ADDRESS,
        PAYMENT,
        COMPLETE
    }
    public void setUserId(Integer userId);
    public Integer getOrderId();
    public String getOrderNumber();
    public String getCreatedAt();
    public String getUpdatedAt();
    public Integer getUserId();
    public void setState(OrderStates state);
    public OrderStates getState();
}
