package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserDB implements IUserPersistence {

    @Autowired
    ConnectionManager connectionManager;

    PreparedStatement preparedStatement;

    @Override
    public Result save(User u) throws Exception {
        try{

            String query = "insert into user (email, first_name, last_name,password, mobile_no) values ( ?, ?, ?, ?, ?);";
            preparedStatement= connectionManager.connection.prepareStatement(query);
            preparedStatement.setString(1,u.getEmail());
            preparedStatement.setString(2,u.getFirstName());
            preparedStatement.setString(3,u.getLastName());
            preparedStatement.setString(4,u.getPassword());
            preparedStatement.setString(5,u.getMobileNo());
            preparedStatement.executeUpdate();

            return Result.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return Result.STORAGE_FAILURE;
        }
        finally {
            connectionManager.connection.close();
        }
    }

    @Override
    public int loadUserID(String email) {
        String query = "select id from user where email = ?;";
        try {
            preparedStatement = connectionManager.connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            int rs1 = preparedStatement.executeUpdate();
            return rs1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadUserPasswordbyUsername(String email){
        String query = "select password from user where email = ?";
        try{
            preparedStatement = connectionManager.connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            String password = String.valueOf(preparedStatement.executeUpdate());
            return password;
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }
    public String loadUsername(String email){
        String query = "select email from user where email = ?";
        try{
            preparedStatement = connectionManager.connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            String result = String.valueOf(preparedStatement.executeUpdate());
            return result;
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
