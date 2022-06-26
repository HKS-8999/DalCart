package dalcart.app.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;

@Repository
public class ProductDB implements IProductPersistence
{
    @Autowired
    private Environment environment;

    Connection conn;

    Statement statement;
    ResultSet resultSet;

    public HashMap<String,String> map;

    @Override
    public HashMap getProductDetails(Product product)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));

            String query = "select * from CSCI5308_2_DEVINT.product_detail;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                map.put("product_id",resultSet.getString(1));
                map.put("product_name",resultSet.getString(2));
                map.put("product_description",resultSet.getString(3));
                map.put("product_price",resultSet.getString(4));
                map.put("product_quantity",resultSet.getString(5));
                map.put("product_picture_url",resultSet.getString(6));
                map.put("is_product_active",resultSet.getString(7));
            }
            return map;

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
