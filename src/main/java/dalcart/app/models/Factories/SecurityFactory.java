package dalcart.app.models.Factories;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.*;

public class SecurityFactory implements ISecurityFactory {

    @Override
    public IAuthenticate createSecurity(IUserPersistence userFactory, IUser user) {
        return new Authentication(userFactory, user);
    }

    public ISecurePassword createSecurePassword() {
        return new SecurePassword();
    }
}
