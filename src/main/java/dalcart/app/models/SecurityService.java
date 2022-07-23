package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
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

    @Override
    public RESULT authenticateUser(IUser user){
        String email = user.getEmail();
        String password = user.getPassword();
        user.loadUserAttributes(userPersistence);

        if(email == null || email.isEmpty() || user.getEmail() == null)
        {
            return RESULT.USERNAME_INVALID;
        }
        else if (password == null || user.getPassword() == null)
        {
            return RESULT.PASSWORD_INVALID;
        }
        else if (user.getEmail().equals(email)  && user.getPassword().equals(password))
        {
            return RESULT.AUTHORIZED;
        }
        else
        {
            return RESULT.IS_NOT_AUTHORIZED;
        }
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