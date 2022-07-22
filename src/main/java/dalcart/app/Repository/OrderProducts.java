package dalcart.app.Repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public
class OrderProducts  {

    Connection connection;
    static PreparedStatement preparedStatement;

    public static boolean saveOrderProduct(Integer orderId, Integer productId){
        try {
            String findQuery = "select * from order_products where order_id = ? and product_id = ?";
            String query = "";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(findQuery);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                //System.out.println("Product Found");
                query = "update order_products set product_quantity = (product_quantity + 1) where order_id = ? and product_id = ?";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, productId);
            }else{
                //System.out.println("Product Not Found");
                query = "insert into order_products (order_id, product_id, product_quantity) values (?,?,?);";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, productId);
                preparedStatement.setInt(3, 1);
            }

            //System.out.println("Updating");

            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
