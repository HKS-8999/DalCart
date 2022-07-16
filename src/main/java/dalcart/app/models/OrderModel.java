package dalcart.app.models;

import dalcart.app.Repository.OrderDB;
import dalcart.app.items.*;
import dalcart.app.utils.CommonUtils;
import dalcart.app.utils.OrderUtils;

public class OrderModel implements IOrderModel {

    private Integer orderId;
    private String orderNumber;
    private String createdAt;
    private String updatedAt;
    private Integer userId;

    OrderState state;
//    public Product p;


    @Override
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Integer getOrderId() {
        return this.orderId;
    }

    @Override
    public String getOrderNumber() {
        return this.orderNumber;
    }

    @Override
    public String getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public String getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public Integer getUserId() {
        return this.userId;
    }

    @Override
    public void setState(OrderState state) {

        this.state = state;
    }

    @Override
    public OrderState getState() {
        return this.state;
    }

    public static IOrderModel getOrderByUserId(Integer userId) {
        return OrderDB.findByUserId(userId);
    }

    public IOrderModel findOrderByNumber(String orderNumber){
        return null;
    }

    public IOrderModel findOrderById(int id){
        return null;
    }

    @Override
    public Integer save()  {
        try {
            if (this.orderNumber == null) {
                this.orderNumber = OrderUtils.generateOrderNumber(); //this will be for the user to see
            }
            if (this.createdAt == null) {
                this.createdAt = CommonUtils.generateTimeStamp();
            }
            this.updatedAt = CommonUtils.generateTimeStamp();
            if (this.validateParameters()) {
                this.orderId = OrderDB.saveOrder(this);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return this.orderId;
    }

    public IOrderModel last(){
        try{
            OrderDB.getLastOrder();
        }catch(Exception e){

        }
        return null;
    }

    public boolean delete(){
        return OrderDB.deleteOrder(this.getOrderId());
    }
    public static IOrderModel getOrderByUserId(){
        return new OrderModel().last();
    }

    private boolean validateParameters(){
        if(this.userId != null){
            return true;
        }
            return false;
    }
}
