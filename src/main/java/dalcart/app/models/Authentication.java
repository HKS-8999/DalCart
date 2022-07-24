package dalcart.app.models;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.models.Security.EmailAndPasswordAuthenticate;
import dalcart.app.models.Security.EmailAuthenticate;
import dalcart.app.models.Security.PasswordAuthenticate;
import dalcart.app.models.Security.Security;

public class Authentication implements IAuthenticate {
    Security handler;
    IUserPersistence userPersistence;
    IUser user;

    public Authentication(IUserPersistence userPersistence, IUser user){
        this.userPersistence = userPersistence;
        this.user = user;
        buildChain(user);
    }

    private void buildChain(IUser user) {
        handler = new EmailAuthenticate(userPersistence,user);
        handler.setNextHandler(new PasswordAuthenticate(userPersistence,user));
        handler.setNextHandler(new EmailAndPasswordAuthenticate(userPersistence,user));
    }
    public Security.RESULT authenticate(IUser user) {
        return handler.authenticate(user);
    }
}
