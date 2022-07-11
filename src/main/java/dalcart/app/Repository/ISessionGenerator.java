package dalcart.app.Repository;

import dalcart.app.models.User;

import java.sql.SQLException;

public interface ISessionGenerator {
    public boolean saveSession(String email, IUserPersistence iUserPersistence) throws SQLException ;
}
