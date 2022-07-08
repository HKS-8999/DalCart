package dalcart.app.Repository;

import dalcart.app.database.ConnectionManager;
import dalcart.app.items.Product;
import dalcart.app.models.IProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class ProductDB implements IProductModel
{
    @Autowired
    private Environment environment;
//
//    @Autowired
//    private ConnectionManager connectionManager;

    Connection conn;

    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public ArrayList getProductDetails(Product product)
    {
        ArrayList<Product> product_detail = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));

            String query = "select * from CSCI5308_2_DEVINT.products;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                Product p = new Product();
                p.setProductId(resultSet.getInt(1));
                p.setProductName(resultSet.getString(2));
                p.setProductDescription(resultSet.getString(3));
                p.setProductQuantity(resultSet.getInt(5));
                p.setProductPrice(resultSet.getInt(4));
                p.setEnabled(resultSet.getBoolean(7));
                product_detail.add(p);
            }
            return product_detail;

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return product_detail;
    }

    public void addProductDetails(Product product)
    {
        try
        {
            LocalDate date = java.time.LocalDate.now();
            String query = "insert into CSCI5308_2_DEVINT.products (product_name, product_description, product_price, product_quantity, product_picture_url, enabled, created_at, updated_at) values ( ?, ?, ?, ?, ?, ?, ?, ?);";


            preparedStatement= ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setString(2,product.getProductDescription());
            preparedStatement.setInt(3,product.getProductPrice());
            preparedStatement.setInt(4,product.getProductQuantity());
            preparedStatement.setString(5,product.getProductPictureUrl());
            preparedStatement.setBoolean(6,product.getEnabled());
            preparedStatement.setString(7, String.valueOf(date));
            preparedStatement.setString(8, "0000-00-00");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateInventory(Product product)
    {
        try
        {
            String query = "update CSCI5308_2_DEVINT.products set product_quantity = " + product.getProductQuantity() + " where id = " + product.getProductId() + ";";
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateStateOfProduct(Product product)
    {
        try
        {
            String query = "update CSCI5308_2_DEVINT.products set enabled = " + product.getEnabled() + " where id = " + product.getProductId() + ";";
            statement =  ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String addProductToCart(Map<String,String> allParams)
    {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));


            String query = "insert into CSCI5308_2_DEVINT.cart (product_id, user_id, created_date, updated_date) values ( ?, ?, ?, ?);";
            preparedStatement = conn.prepareStatement(query);
//            connectionManager.ConnectionManager();
//            preparedStatement = connectionManager.connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(allParams.get("id")));
            preparedStatement.setInt(2,Integer.parseInt(allParams.get("userkey")));
            preparedStatement.setString(3,"0000-00-00");
            preparedStatement.setString(4,"0000-00-00");
            preparedStatement.executeUpdate();
            return "Success";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Failure";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Failure";
        }
    }
}