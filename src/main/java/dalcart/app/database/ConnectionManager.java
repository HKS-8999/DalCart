package dalcart.app.database;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionManager {


    Environment environment;
    private static ConnectionManager instance;

    private static Connection connection;

    private ConnectionManager() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(this.connection == null || this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));
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

    public Connection getConnection() {
        return this.connection;
    }

    public static ConnectionManager getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void begin() throws SQLException {
        this.connection.setAutoCommit(false);
    }
    public void commit() throws SQLException {
        this.connection.commit();
        //this.connection.close();
    }
    public void rollback() throws SQLException {
        this.connection.rollback();
        //this. connection.close();
    }
}
