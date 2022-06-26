package dalcart.app.Repository;

import dalcart.app.models.User;

import java.sql.SQLException;

public interface ISessionGenerator {
    public boolean generateSession(User user, IUserPersistence iUserPersistence) throws SQLException ;
}
