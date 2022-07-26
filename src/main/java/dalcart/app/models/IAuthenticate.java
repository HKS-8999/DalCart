package dalcart.app.models;

import dalcart.app.models.Security.Security;

public interface IAuthenticate {
    Security.RESULT authenticate(IUser user);
}
