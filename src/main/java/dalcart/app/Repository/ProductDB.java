package dalcart.app.Repository;

import dalcart.app.models.Product;
import dalcart.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductDB implements IProductService
{
    @Autowired
    private Environment environment;

    Connection conn;

    Statement statement;
    ResultSet resultSet;

    ArrayList<Product> product_detail = new ArrayList<>();

    public ArrayList getProductDetails(Product product)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));

            String query = "select * from CSCI5308_2_DEVINT.product_detail;";
//            String query = "select * from product_details;";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                Product p = new Product();
                p.setProductName(resultSet.getString(2));
                p.setProductDescription(resultSet.getString(3));
                p.setProductQuantity(resultSet.getInt(5));
                p.setProductPrice(resultSet.getInt(4));
                product_detail.add(p);
            }
            return product_detail;

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return product_detail;
    }
}
