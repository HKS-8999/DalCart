package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.Validation.EmailAndPasswordAuthenticate;
import dalcart.app.models.Validation.EmailAuthenticate;
import dalcart.app.models.Validation.PasswordAuthenticate;
import dalcart.app.models.Validation.Security;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Service
public class SecurityService implements ISecurity {
    private static final String admin = "admin";
    private static final String user = "user";
    IUserPersistence userPersistence;

    public SecurityService(IUserPersistence userPersistence){
        this.userPersistence = userPersistence;
    }

    public static boolean isSessionValid(HttpSession session)
    {
        Enumeration<String> names = session.getAttributeNames();
        if(names.hasMoreElements())
        {
            return true;
        }
        return false;
    }

    public boolean isUserRoleAdmin(HttpSession session)
    {
        Enumeration<String> names = session.getAttributeNames();
        if(names.nextElement().equals(admin)){
            return true;
        }
        return false;
    }

    public boolean isUserRoleUser(HttpSession session)
    {
        Enumeration<String> names = session.getAttributeNames();
        if(names.nextElement().equals(user)){
            return true;
        }
        return false;
    }
}