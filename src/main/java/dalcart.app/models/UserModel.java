package dalcart.app.models;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


// @Controller
// @RequestMapping(value = {"/admin"})
@Component
public class UserModel extends Model {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Autowired
  private DataSource dataSource;

    // @GetMapping(value = {""})
    public String getData() {
        //String db(Map<String, Object> model) {
            System.out.println("Reached Here 3"); 
          try (Connection connection = dataSource.getConnection()) {
            System.out.println("Reached Here 1");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
            System.out.println("Reached Here 2");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
              output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            // model.put("records", output);
            return "db123";
          } catch (Exception e) {
            // model.put("message", e.getMessage());
            e.printStackTrace();
            return "error";
          }
      //}
    }


    @Bean
    public DataSource dataSource() throws SQLException {
      if (dbUrl == null || dbUrl.isEmpty()) {
        return new HikariDataSource();
      } else {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
      }
    }

}