package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;

import dalcart.app.models.IUser;
import dalcart.app.models.Security;
import dalcart.app.models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserDB implements IUserPersistence {

    ResultSet resultset;

    PreparedStatement preparedStatement;

    public UserDB() {

    }
    @Override
    public Integer save(IUser u) throws Exception
    {
        try
        {
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

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IUser loadUserAttributesByUserId(int userId)
    {
        String query = "select * from user where id = ?;";
        try
        {
            IUser user = new User();
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            System.out.println(preparedStatement);
            resultset = preparedStatement.executeQuery();
            while (resultset.next())
            {
                int userID = resultset.getInt("id");
                String emailID = resultset.getString("email");
                String firstName = resultset.getString("first_name");
                String lastName = resultset.getString("last_name");
                String password = resultset.getString("password");
                String mobileNo = resultset.getString("mobile_no");
                String designation = resultset.getString("designation");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setMobileNo(mobileNo);
                user.setUserID(userID);
                user.setPassword(password);
                user.setEmail(emailID);
                user.setDesignation(designation);
            }
            return user;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IUser loadUserAttributesbyUsername(String email) {
        String query = "select * from user where email = ?;";
        try {
            String result = null;
            IUser user = new User();

            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            System.out.println(preparedStatement);
            resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int userID = resultset.getInt("id");
                String emailID = resultset.getString("email");
                String firstName = resultset.getString("first_name");
                String lastName = resultset.getString("last_name");
                String password = resultset.getString("password");
                String mobileNo = resultset.getString("mobile_no");
                String designation = resultset.getString("designation");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setMobileNo(mobileNo);
                user.setUserID(userID);
                user.setPassword(password);
                user.setEmail(emailID);
                user.setDesignation(designation);
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
