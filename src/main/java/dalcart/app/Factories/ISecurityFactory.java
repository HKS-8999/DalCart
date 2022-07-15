package dalcart.app.Factories;

import dalcart.app.models.Security;

public interface ISecurityFactory {
    public Security createSecurity(IUserPersistanceFactory userFactory);
}
