package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.models.IOrderModel;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public
class OrderProducts  {

    Connection connection;
    static PreparedStatement preparedStatement;

    public static boolean saveOrderProduct(Integer orderId, Integer productId){
        String query = "insert into order_products (order_id, product_id) values (?,?);";
        try {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
