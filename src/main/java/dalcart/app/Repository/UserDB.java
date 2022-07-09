package dalcart.app.Repository;

import dalcart.app.Repository.IUserPersistence;
import dalcart.app.database.ConnectionManager;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserDB implements IUserPersistence {

    @Autowired
    private Environment environment;

    @Autowired
    ConnectionManager connectionManager;

    PreparedStatement preparedStatement;

    @Override
    public StorageResult save(User u) throws Exception {
        try{

            String query = "insert into CSCI5308_2_DEVINT.User (email, first_name, last_name,password, mobile_no) values ( ?, ?, ?, ?, ?);";
            preparedStatement= connectionManager.connection.prepareStatement(query);
            preparedStatement.setString(1,u.getEmail());
            preparedStatement.setString(2,u.getFirstName());
            preparedStatement.setString(3,u.getLastName());
            preparedStatement.setString(4,u.getPassword());
            preparedStatement.setString(5,u.getMobileNo());
            preparedStatement.executeUpdate();

            return StorageResult.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }
    }

    @Override
    public StorageResult load(String email, User u) {
        return null;
    }

}
