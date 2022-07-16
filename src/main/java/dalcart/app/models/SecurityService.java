package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Service
public class SecurityService implements Security {
    IUserPersistence userPersistence;

    public SecurityService(IUserPersistence userPersistence){
        this.userPersistence = userPersistence;
    }

    @Override
    public RESULT authenticateUser(IUser user){
        String email = user.getEmail();
        String password = user.getPassword();
        user.loadUserAttributes(userPersistence);

        if(email == null || email.isEmpty() || user.getEmail() == null){
            return RESULT.USERNAME_INVALID;
        }
        else if (password == null || user.getPassword() == null) {
            return RESULT.PASSWORD_INVALID;
        }
        else if (user.getEmail().equals(email)  && user.getPassword().equals(password)){
            return RESULT.AUTHORIZED;
        }
        else{
            return RESULT.IS_NOT_AUTHORIZED;
        }
    }

    public static boolean isSessionValid(HttpSession session){
//        String userID = session.getAttribute("user").toString();
//        String admin = session.getAttribute("admin").toString();
//
//
//        if(userID == null || admin == null){
//            return false;
//        }
//        return true;

            Enumeration<String> names = session.getAttributeNames();

            if(names.hasMoreElements()){
                return true;
            }
            return false;
    }
}