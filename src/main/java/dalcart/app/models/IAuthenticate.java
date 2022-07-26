package dalcart.app.models;

import dalcart.app.models.SecurityChain.Security;

public interface IAuthenticate {
    Security.RESULT authenticate(IUser user);
}
