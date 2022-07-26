package dalcart.app.models;

import dalcart.app.models.Repository.OrderDB;
import dalcart.app.controllers.order_states.OrderState;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class OrderModel implements IOrderModel {

    private static final String ORDER_SUBSCRIPT = "W";
    private static final int DEFAULT_LENGTH = 7;
    OrderState state;
    private Integer orderId;
    private String orderNumber;
    private String createdAt;
    private String updatedAt;
    private Integer userId;

    public static IOrderModel getOrderByUserId(Integer userId) {
        return OrderDB.findByUserId(userId);
    }

    public static IOrderModel getOrderByUserId() {
        return new OrderModel().last();
    }

    @Override
    public Integer getOrderId() {
        return this.orderId;
    }

    @Override
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public OrderState getState() {
        return this.state;
    }

    @Override
    public void setState(OrderState state) {

        this.state = state;
    }

    public IOrderModel findOrderByNumber(String orderNumber) {
        return null;
    }

    public IOrderModel findOrderById(int id) {
        return null;
    }

    @Override
    public Integer save() {
        try {
            if (this.orderNumber == null) {
                this.orderNumber = generateOrderNumber();
            }
            if (this.createdAt == null) {
                this.createdAt = generateTimeStamp();
            }
            this.updatedAt = generateTimeStamp();
            if (this.validateParameters()) {
                this.orderId = OrderDB.saveOrder(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return this.orderId;
    }

    public IOrderModel last() {
        try {
            OrderDB.getLastOrder();
        } catch (Exception e) {

        }
        return null;
    }

    public boolean delete() {
        return OrderDB.deleteOrder(this.getOrderId());
    }

    private boolean validateParameters() {
        return this.userId != null;
    }

    public String generateTimeStamp() {
        Date now = new java.util.Date();
        Timestamp current = new java.sql.Timestamp(now.getTime());
        return current.toString();
    }

    public String generateOrderNumber() {
        byte[] array = new byte[DEFAULT_LENGTH];
        new Random().nextBytes(array);
        String generatedString = UUID.randomUUID().toString().toUpperCase().substring(0, DEFAULT_LENGTH);
        return ORDER_SUBSCRIPT + generatedString;
    }
}
