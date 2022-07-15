package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.Security;

public interface ISecurityFactory {
    public Security createSecurity(IUserPersistence userPersistance);
}
