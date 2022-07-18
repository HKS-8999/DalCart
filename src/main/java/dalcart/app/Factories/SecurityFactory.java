package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.Security;
import dalcart.app.models.SecurityService;

public class SecurityFactory implements ISecurityFactory{

    @Override
    public Security createSecurity(IUserPersistence userFactory) {
        return new SecurityService(userFactory);
    }

}
