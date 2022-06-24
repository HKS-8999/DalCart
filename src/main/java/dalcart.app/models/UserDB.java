package dalcart.app.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserDB implements IUserPersistence {

    @Autowired
    private Environment environment;

    Connection conn;

    PreparedStatement preparedStatement;

    @Override
    public StorageResult save(User u) throws Exception {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));
            String query = "insert into CSCI5308_2_DEVINT.User (Email, Password, FirstName, LastName, Mobile_No) values ( ?, ?, ?, ?, ?);";
            preparedStatement= conn.prepareStatement(query);
            preparedStatement.setString(1,u.getEmail());
            preparedStatement.setString(2,u.getPassword());
            preparedStatement.setString(3,u.getFirstName());
            preparedStatement.setString(4,u.getLastName());
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
