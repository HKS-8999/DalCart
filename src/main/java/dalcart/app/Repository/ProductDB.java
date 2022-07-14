package dalcart.app.Repository;

import dalcart.app.controllers.OrderController;
import dalcart.app.database.ConnectionManager;
import dalcart.app.items.IProduct;
import dalcart.app.items.Product;
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
    ProductModel productModel;
    OrderController orderController = new OrderController();
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String tableName = "products";

    public ArrayList getProductDetails()
    {
            ArrayList<IProduct> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    Product p = new Product();
                    p.setProductId(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setProductDescription(resultSet.getString(3));
                    p.setProductQuantity(resultSet.getInt(5));
                    p.setProductPrice(resultSet.getInt(4));
                    p.setEnabled(resultSet.getBoolean(7));
                    p.setProductImage(resultSet.getString(8));
                    product_detail.add(p);
                }
                return product_detail;

            }catch (SQLException e)
            {
                e.printStackTrace();
                return null;
            }
    }

    public ArrayList getProductDetailsForDisplay(String keyword)
    {
        if(keyword == null || keyword == "")
        {
            ArrayList<IProduct> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " where enabled = 1 ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    Product p = new Product();
                    p.setProductId(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setProductDescription(resultSet.getString(3));
                    p.setProductQuantity(resultSet.getInt(5));
                    p.setProductPrice(resultSet.getInt(4));
                    p.setEnabled(resultSet.getBoolean(7));
                    p.setProductImage(resultSet.getString(8));
                    product_detail.add(p);
                }
                return product_detail;

            }catch (SQLException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            ArrayList<Product> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " where product_name like '%" + keyword + "%' ;";
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
                    p.setEnabled(resultSet.getBoolean(7));
                    p.setProductImage(resultSet.getString(8));
                    product_detail.add(p);
                    System.out.println(p.getProductId());
                }
                return product_detail;

            }catch(SQLException e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }

    public IProduct getProductById(Integer productId)
    {
        try {
            String query = "select * from " + tableName + " where id = " + productId + ";";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            IProduct p = new Product();
            while (resultSet.next()) {
                p.setProductId(resultSet.getInt(1));
                p.setProductName(resultSet.getString(2));
                p.setProductDescription(resultSet.getString(3));
                p.setProductQuantity(resultSet.getInt(5));
                p.setProductPrice(resultSet.getInt(4));
                p.setEnabled(resultSet.getBoolean(7));
                p.setProductImage(resultSet.getString(8));
            }
            return p;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public Integer getLastProductId()
    {
        try
        {
            String query = "select id from " + tableName + " ;";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            resultSet.last();
            Integer productId = resultSet.getInt(1);
            return productId;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void saveProduct(ProductModel product)
    {
        Integer id = getLastProductId();
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
            preparedStatement.setBoolean(6,product.getEnabled());
            preparedStatement.setString(7, String.valueOf(date));
            preparedStatement.setString(8, "0000-00-00");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
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

    public void addProductToCart(Map<String, String> parameters) throws SQLException
    {
        IProduct[] products = new IProduct[1];
        User user = new User();
        user.setUserId(1);
        user.setEmail("zdssd");
        user.setFirstName("dfdfv");
        user.setLastName("ascc");
        user.setPassword("12345");
        user.setMobileNo("9875412368");
        products[0] = getProductById(Integer.valueOf(parameters.get("id")));
        //        System.out.println(user.getUserID());
//        System.out.println(products[0]);
//        System.out.println(products[0].getProductId());
        orderController.addToOrder(user, products);
    }
}
