package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.ISecurity;
import dalcart.app.models.SecurityService;

public class SecurityFactory implements ISecurityFactory{

    @Override
    public ISecurity createSecurity(IUserPersistence userFactory) {
        return new SecurityService(userFactory);
    }

}
