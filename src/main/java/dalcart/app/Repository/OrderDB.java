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
class OrderDB  {

    Connection connection;
    static PreparedStatement preparedStatement;

    public static Integer saveOrder(IOrderModel order) throws SQLException {
            String query = "insert into orders (user_id, created_at, updated_at, order_number) values (?,?,?,?);";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,order.getUserId());
            preparedStatement.setString(2,order.getCreatedAt());
            preparedStatement.setString(3,order.getUpdatedAt());
            preparedStatement.setString(4,order.getOrderNumber());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
    }

    public static boolean deleteOrder(Integer orderId) {
        try {
            String query = "delete from orders where id = ?;";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,orderId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
