package dalcart.app.models.Factories;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.IAuthenticate;
import dalcart.app.models.ISecurePassword;
import dalcart.app.models.IUser;

public interface ISecurityFactory {
    IAuthenticate createSecurity(IUserPersistence userPersistance, IUser user);

    ISecurePassword createSecurePassword();
}
