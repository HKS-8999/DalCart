package dalcart.app.items;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


// @Controller
// @RequestMapping(value = {"/admin"})


@Component
public class UserModel extends trashed {


    public String getData() {
        //String db(Map<String, Object> model) {
          try (Connection connection = super.dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

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

    public String sendData() {
        //String db(Map<String, Object> model) {
          try (Connection connection = super.dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

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


}