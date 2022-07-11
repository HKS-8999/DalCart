package dalcart.app.utils;

import java.sql.Timestamp;
import java.util.Date;

public class CommonUtils {
    public static String generateTimeStamp(){
        Date now = new java.util.Date();
        Timestamp current = new java.sql.Timestamp(now.getTime());
        return current.toString();
    }
}
