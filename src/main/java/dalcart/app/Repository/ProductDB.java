package dalcart.app.Repository;

import dalcart.app.controllers.OrderController;
import dalcart.app.database.ConnectionManager;
import dalcart.app.items.IProduct;
import dalcart.app.items.Product;
import dalcart.app.models.IUser;
import dalcart.app.models.ProductModel;
import dalcart.app.models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class ProductDB
{
//    Connection connection;
    ProductModel productModel;
    OrderController orderController = new OrderController();
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String tableName = "products";
//    static String tableName = "CSCI5308_2_DEVINT.products";

    public ArrayList getProductDetails(Product product)
    {
        ArrayList<Product> product_detail = new ArrayList<>();
        try
        {
            String query = "select * from " + tableName + " ;";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
//            statement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next())
            {
                Product p = new Product();
                p.setProductId(resultSet.getInt(1));
                p.setProductName(resultSet.getString(2));
                p.setProductDescription(resultSet.getString(3));
                p.setProductQuantity(resultSet.getInt(5));
                p.setProductPrice(resultSet.getInt(4));
                p.setProductState(resultSet.getBoolean(7));
                p.setProductImage(resultSet.getString(8));
                product_detail.add(p);
            }
            return product_detail;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return product_detail;
    }

    public IProduct getProductById(Integer productId) throws SQLException
    {
        String query = "select * from " + tableName + " where id = " + productId + ";";
        preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
        resultSet = preparedStatement.executeQuery(query);
        IProduct p = new Product();
        while(resultSet.next())
        {
            p.setProductId(resultSet.getInt(1));
            p.setProductName(resultSet.getString(2));
            p.setProductDescription(resultSet.getString(3));
            p.setProductQuantity(resultSet.getInt(5));
            p.setProductPrice(resultSet.getInt(4));
            p.setProductState(resultSet.getBoolean(7));
            p.setProductImage(resultSet.getString(8));
        }
        return p;
    }

    public void saveProduct(ProductModel product)
    {
        try
        {
            LocalDate date = java.time.LocalDate.now();
            String query = "insert into " + tableName + " (product_name, product_description, product_price, product_quantity, product_picture_url, enabled, created_at, updated_at) values ( ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement= ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setString(2,product.getProductDescription());
            preparedStatement.setInt(3,product.getProductPrice());
            preparedStatement.setInt(4,product.getProductQuantity());
            preparedStatement.setString(5,product.getProductImage());
            preparedStatement.setBoolean(6,product.getProductState());
            preparedStatement.setString(7, String.valueOf(date));
            preparedStatement.setString(8, "0000-00-00");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
        try
        {
            String query = "update " + tableName + " set product_quantity = " + productQuantity + ", set enabled = " + productState + " where id = " + productId + ";";
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addProductToCart(Map<String, String> parameters) throws SQLException {
        IProduct[] products = new IProduct[1];
        User user = new User();
        user.setUserId(1);
        user.setEmail("zdssd");
        user.setFirstName("dfdfv");
        user.setLastName("ascc");
        user.setPassword("12345");
        user.setMobileNo("9875412368");
        try
        {
            products[0] = getProductById(Integer.valueOf(parameters.get("id")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println(user.getUserID());
        System.out.println(products[0]);
        System.out.println(products[0].getProductId());
        orderController.addToOrder(user, products);
    }


//    @Override
//    public String addProductToCart(Map<String,String> allParams)
//    {
//        try{
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
//                    environment.getProperty("spring.datasource.username"),
//                    environment.getProperty("spring.datasource.password"));
//
//
//            String query = "insert into CSCI5308_2_DEVINT.cart (product_id, user_id, created_date, updated_date) values ( ?, ?, ?, ?);";
//            preparedStatement = conn.prepareStatement(query);
////            connectionManager.ConnectionManager();
////            preparedStatement = connectionManager.connection.prepareStatement(query);
//            preparedStatement.setInt(1, Integer.parseInt(allParams.get("id")));
//            preparedStatement.setInt(2,Integer.parseInt(allParams.get("userkey")));
//            preparedStatement.setString(3,"0000-00-00");
//            preparedStatement.setString(4,"0000-00-00");
//            preparedStatement.executeUpdate();
//            return "Success";
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//            return "Failure";
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return "Failure";
//        }
//    }
    
}
