package dalcart.app.Factories;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.ISecurity;

public interface ISecurityFactory {
    public ISecurity createSecurity(IUserPersistence userPersistance);
}
