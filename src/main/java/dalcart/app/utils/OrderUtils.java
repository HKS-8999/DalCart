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
}