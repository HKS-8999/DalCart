package dalcart.app.Repository;

import java.sql.SQLException;

public interface ISessionGenerator {
    public boolean saveSession(String email) throws SQLException ;
}
