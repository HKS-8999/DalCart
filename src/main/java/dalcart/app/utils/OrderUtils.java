package dalcart.app.utils;

import dalcart.app.models.IOrderModel;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class OrderUtils {
    private static final String ORDER_SUBSCRIPT = "W";
    private static final int DEFAULT_LENGTH = 7;

    public static String generateOrderNumber() {
        byte[] array = new byte[DEFAULT_LENGTH]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = UUID.randomUUID().toString().toUpperCase().substring(0,DEFAULT_LENGTH);
        return ORDER_SUBSCRIPT + generatedString;
    }

    public static IOrderModel.OrderStates getState(String orderState){
        String state = orderState.toLowerCase();
        if(state.equals("cart")){
            return IOrderModel.OrderStates.CART;
        }else if(state.equals("address")){
            return IOrderModel.OrderStates.ADDRESS;
        }else if(state.equals("payment")){
            return IOrderModel.OrderStates.PAYMENT;
        }else if(state.equals("complete")){
            return IOrderModel.OrderStates.COMPLETE;
        }
        //if there is no state we should consider that it is in cart state
        return IOrderModel.OrderStates.CART;
    }

    public static String getStateString(IOrderModel.OrderStates state){
        if(state.equals(IOrderModel.OrderStates.CART)){
            return "cart";
        }else if(state.equals(IOrderModel.OrderStates.ADDRESS)){
            return "address";
        }else if(state.equals(IOrderModel.OrderStates.PAYMENT)){
            return "payment";
        }else if(state.equals(IOrderModel.OrderStates.COMPLETE)){
            return "ccomplete";
        }
        return null;
    }

}