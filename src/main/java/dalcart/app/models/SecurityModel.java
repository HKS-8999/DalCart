package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import org.springframework.stereotype.Service;

@Service
public class SecurityModel implements Security {
    IUserPersistence userPersistence;

    public SecurityModel(IUserPersistence userPersistence){
        this.userPersistence = userPersistence;
    }

    @Override
    public RESULT authenticateUser(String email, String password){
        if(userPersistence.loadUsername(email) == null || userPersistence.loadUsername(email).isEmpty()){
            return RESULT.USERNAME_INVALID;
        }

        else if (userPersistence.loadUserPasswordbyUsername(email)==null) {
            return RESULT.PASSWORD_INVALID;
        }
        else if (userPersistence.loadUsername(email).equals(email)  && userPersistence.loadUserPasswordbyUsername(password).equals(password)){
            return RESULT.AUTHORIZED;
        }
        else{
            return RESULT.IS_NOT_AUTHORIZED;
        }
    }
}