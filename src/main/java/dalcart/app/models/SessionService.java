package dalcart.app.models;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import dalcart.app.Repository.IUserPersistence;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;

@Service
public class SessionService implements ISession {
    private static final String adminRole = "admin";
    private static final String userRole = "user";

    IUserPersistence userPersistence;

    public SessionService(IUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public static boolean isSessionValid(HttpSession session) {
        Enumeration<String> names = session.getAttributeNames();
        if (names.hasMoreElements()) {
            return true;
        }
        return false;
    }

    public boolean isAdminInSession(HttpSession session) {
        Enumeration<String> names = session.getAttributeNames();
        while(names.hasMoreElements()){
            if (names.nextElement().equals(adminRole)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserInSession(HttpSession session) {
        try {
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                if (names.nextElement().equals(userRole)) {
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}