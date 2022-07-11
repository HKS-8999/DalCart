package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.database.IOpenConnection;
import dalcart.app.models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDB implements IUserPersistence {



    Connection connection;

    ResultSet resultset;

    PreparedStatement preparedStatement;

    public UserDB(){

    }

    @Override
    public Integer save(User u) throws Exception {
        try {

            String query = "insert into user (email, first_name, last_name,password, mobile_no) values ( ?, ?, ?, ?, ?);";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, u.getEmail());
            preparedStatement.setString(2, u.getFirstName());
            preparedStatement.setString(3, u.getLastName());
            preparedStatement.setString(4, u.getPassword());
            preparedStatement.setString(5, u.getMobileNo());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int loadUserID(String email) {
        String query = "select id from user where email = ?;";
        try {
            int result = 0;
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,email);
            resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                result = resultset.getInt("id");
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loadUserPasswordbyUsername(String email){
        String query = "select email from user where email = ?;";
        try{
            String result="";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,email);
            System.out.println(preparedStatement);
            resultset = preparedStatement.executeQuery();

            System.out.println(resultset+"xxx");
            while(resultset.next()){
                result = resultset.getString("email");
            }
            return result;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public String loadUsername(String email){
        String query = "select email from user where email = ?;";
        try{
            String result = null;
            System.out.println(email);
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,email);
            System.out.println(preparedStatement);
            resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                result = resultset.getString("email");
            }
            return result;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
