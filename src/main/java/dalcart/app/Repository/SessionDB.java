package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.models.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public
class SessionDB implements ISessionGenerator {

    Connection connection;
    PreparedStatement preparedStatement;
    IUserPersistence iUserPersistence;

    public SessionDB(){

    }

    @Override
    public boolean saveSession(String email) throws SQLException {
        String query = "insert into session (user_id, token, expiry) values (?,?,?);";
        preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setInt(1,iUserPersistence.loadUserID(email));
        preparedStatement.setString(2, UUID.randomUUID().toString());
        preparedStatement.setString(3," ADDTIME( now(), '0:15:00')");
        preparedStatement.executeUpdate();
        return true;
    }
}
