package dalcart.app.database;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionManager  {
    private static ConnectionManager instance = null;
    private Connection connection = null;

    private ConnectionManager() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection( properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));
            System.out.println("Connection Success");

        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionManager();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionManager();
        }
        return instance;
    }
}
