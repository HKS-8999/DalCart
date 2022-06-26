package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class SessionDB implements ISessionGenerator {
    ConnectionManager connectionManager;
    PreparedStatement preparedStatement;

    @Override
    public boolean generateSession(User user, IUserPersistence iUserPersistence) throws SQLException {
        String query = "insert into session (user_id, token, expiry) values (?,?,?);";
        preparedStatement = connectionManager.connection.prepareStatement(query);
        preparedStatement.setInt(1,iUserPersistence.loadUserID(user));
        preparedStatement.setString(2, UUID.randomUUID().toString());
        preparedStatement.setString(3," ADDTIME( now(), '0:15:00')");
        preparedStatement.executeUpdate();
        return true;
    }
}
