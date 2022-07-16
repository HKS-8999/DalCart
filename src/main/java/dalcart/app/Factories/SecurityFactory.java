package dalcart.app.Factories;

import dalcart.app.models.Security;
import dalcart.app.models.SecurityService;

public class SecurityFactory implements ISecurityFactory{

    @Override
    public Security createSecurity(IUserPersistanceFactory userFactory) {
        return new SecurityService(userFactory.createIUserPersistance());
    }

}
