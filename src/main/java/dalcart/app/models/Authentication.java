package dalcart.app.models;

import dalcart.app.models.Repository.IUserPersistence;
import dalcart.app.models.SecurityChain.EmailAndPasswordAuthenticate;
import dalcart.app.models.SecurityChain.EmailAuthenticate;
import dalcart.app.models.SecurityChain.PasswordAuthenticate;
import dalcart.app.models.SecurityChain.Security;

public class Authentication implements IAuthenticate {
    Security handler;
    IUserPersistence userPersistence;
    IUser user;

    public Authentication(IUserPersistence userPersistence, IUser user) {
        this.userPersistence = userPersistence;
        this.user = user;
        buildChain(user);
    }

    private void buildChain(IUser user) {
        handler = new EmailAuthenticate(userPersistence, user);
        handler.setNextHandler(new PasswordAuthenticate(userPersistence, user));
        handler.setNextHandler(new EmailAndPasswordAuthenticate(userPersistence, user));
    }

    public Security.RESULT authenticate(IUser user) {
        return handler.authenticate(user);
    }
}
