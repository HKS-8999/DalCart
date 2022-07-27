package dalcart.app.models.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    private Connection connection = null;

    private ConnectionManager() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");

            String database_url      = System.getenv("SPRING_DATASOURCE_URL");
            String database_username = System.getenv("SPRING_DATASOURCE_USERNAME");
            String database_password = System.getenv("SPRING_DATASOURCE_PASSWORD");
            

            System.out.println("Database URL:**********"  + database_url);

            if(this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(database_url, database_username, database_password);
                System.out.println("Connection Success");
            }else{
                System.out.println("Connection already alive");
            }
        } catch (SQLException | IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionManager getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void begin() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
    }
}
