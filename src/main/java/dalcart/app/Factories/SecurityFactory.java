package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.*;

public class SecurityFactory implements ISecurityFactory{

    @Override
    public IAuthenticate createSecurity(IUserPersistence userFactory, IUser user) {
        //return new SessionService(userFactory);
        return new Authentication(userFactory,user);
    }
    public ISecurePassword createSecurePassword() {return new SecurePassword();}
}
