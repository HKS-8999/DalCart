package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import org.springframework.stereotype.Service;

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
        user.loadUserAttributes(user,userPersistence);

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
}