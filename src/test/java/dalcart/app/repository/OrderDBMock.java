package dalcart.app.repository;

import dalcart.app.models.Repository.ConnectionManager;
import dalcart.app.controllers.order_states.*;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import dalcart.app.models.OrderModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class OrderDBMock  {

    Connection connection;
    static PreparedStatement preparedStatement;
    static Statement statement;

    public static OrderState getStateByName(String orderState){
        String state = orderState.toLowerCase();
        if(state.equals("cart")){
            return (new OrderAtCart());
        }else if(state.equals("address")){
            return (new OrderAtAddress());
        }else if(state.equals("payment")){
            return (new OrderAtPayment());
        }else if(state.equals("complete")){
            return (new OrderAtComplete());
        }
        return (new OrderAtCart());
    }

    public static Integer saveOrder(IOrderModel order) throws SQLException {
        String query = "insert into orders (user_id, created_at, updated_at, order_number, state) values (?,?,?,?,?);";
        preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,order.getUserId());
        preparedStatement.setString(2,order.getCreatedAt());
        preparedStatement.setString(3,order.getUpdatedAt());
        preparedStatement.setString(4,order.getOrderNumber());
        preparedStatement.setString(5,order.getState().getStateName());
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

    public static IOrderModel getLastOrder(){
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        try{

            String query = "select * from orders order by id desc limit 1";
            Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            IOrderModel order = new OrderModel();
            while(resultSet.next())
            {
                order.setOrderId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setState(getStateByName(resultSet.getString(3)));
            }
            return order;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static IOrderModel findByUserId(int userId){
        //ArrayList<IProductModel> product_detail = new ArrayList<>();
        try{

            String query = "select * from orders where user_id = " + userId + " limit 1";
            Statement statement = ConnectionManager.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            IOrderModel order = new OrderModel();
            boolean resultSetNotEmpty = false;
            while(resultSet.next())
            {
                resultSetNotEmpty = true;
                order.setOrderId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setState(getStateByName(resultSet.getString(3)));
            }
            if(resultSetNotEmpty) {
                return order;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public IOrderModel findOrderInCartByUserId(int userId)
    {
        try {

            String query = "select * from orders where user_id = " + userId + " and state = 'cart';";
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            IOrderModel order = new OrderModel();
            boolean resultSetNotEmpty = false;
            while (resultSet.next()) {
                resultSetNotEmpty = true;
                order.setOrderId(resultSet.getInt(1));
                order.setUserId(resultSet.getInt(2));
                order.setState(getStateByName(resultSet.getString(3)));
            }
            if (resultSetNotEmpty) {
                return order;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean removeProductFromCart(Integer orderId, Integer productId){
        try {

            String query = "delete from order_products where order_id = ? and product_id = ?";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,orderId);
            preparedStatement.setInt(2,productId);
            preparedStatement.executeUpdate();

            query = "delete from orders where id in (select ord.id from orders as ord left join order_products as ordp on ord.id = ordp.order_id where ordp.order_id is null)";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
