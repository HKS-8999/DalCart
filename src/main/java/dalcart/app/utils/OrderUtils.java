package dalcart.app.utils;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class OrderUtils {
    private static final String orderSubscript = "W";
    private static final int defaultLength = 7;

    public static String generateOrderNumber() {
        byte[] array = new byte[defaultLength]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return orderSubscript + generatedString;
    }

}