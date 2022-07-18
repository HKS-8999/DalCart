package dalcart.app.Repository;

import com.fasterxml.jackson.core.JsonToken;
import dalcart.app.controllers.OrderController;
import dalcart.app.database.ConnectionManager;
import dalcart.app.models.IProductDB;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;
import dalcart.app.models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class ProductDB implements IProductDB
{
    OrderController orderController = new OrderController();
    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String tableName = "products";

    public ArrayList getProductDetails()
    {
            ArrayList<IProductModel> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    ProductModel p = new ProductModel();
                    p.setProductId(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setProductDescription(resultSet.getString(3));
                    p.setProductPrice(resultSet.getInt(4));
                    p.setProductQuantity(resultSet.getInt(5));
                    p.setProductImage(resultSet.getString(6));
                    p.setEnabled(resultSet.getBoolean(7));

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
            ArrayList<IProductModel> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " where enabled = 1 ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    ProductModel p = new ProductModel();
                    p.setProductId(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setProductDescription(resultSet.getString(3));
                    p.setProductPrice(resultSet.getInt(4));
                    p.setProductQuantity(resultSet.getInt(5));
                    p.setProductImage(resultSet.getString(6));
//                    p.setEnabled(resultSet.getBoolean(7));
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
            ArrayList<ProductModel> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " where product_name like '%" + keyword + "%' ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
//            statement = connection.createStatement();
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    ProductModel p = new ProductModel();
                    p.setProductId(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setProductDescription(resultSet.getString(3));
                    p.setProductPrice(resultSet.getInt(4));
                    p.setProductQuantity(resultSet.getInt(5));
                    p.setProductImage(resultSet.getString(6));
//                    p.setEnabled(resultSet.getBoolean(7));

                    product_detail.add(p);
//                    System.out.println(p.getProductId());
                }
                return product_detail;

            }catch(SQLException e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }

    public IProductModel getProductById(Integer productId)
    {
        try {
            String query = "select * from " + tableName + " where id = " + productId + ";";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            IProductModel p = new ProductModel();
            while (resultSet.next()) {
                p.setProductId(resultSet.getInt(1));
                p.setProductName(resultSet.getString(2));
                p.setProductDescription(resultSet.getString(3));
                p.setProductPrice(resultSet.getInt(4));
                p.setProductQuantity(resultSet.getInt(5));
                p.setProductImage(resultSet.getString(6));
                p.setEnabled(resultSet.getBoolean(7));
            }
            System.out.println(p.getProductId());
            System.out.println(p.getProductName());
            System.out.println(p.getProductDescription());
            System.out.println(p.getProductPrice());
            System.out.println(p.getProductQuantity());
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
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery(query);
            resultSet.last();
            Integer productId = resultSet.getInt(1);
            System.out.println(productId);
            return productId;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void saveProduct(IProductModel product)
    {
        product.setProductId(4);
        product.setProductName("Hoodies");
        product.setProductDescription("Woolen Winter hoodies");
        product.setProductPrice(60);
        product.setProductQuantity(25);
        product.setProductImage("abc.jpg");
        product.setEnabled(true);

//        Integer id = getLastProductId();
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
            if(product.getEnabled())
            {
                preparedStatement.setInt(6,1);
            }
            else
            {
                preparedStatement.setInt(6,0);
            }
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
        int state;
        try
        {
            LocalDate date = java.time.LocalDate.now();
            if(productState)
            {
                state = 1;
            }
            else
            {
                state = 0;
            }
            String query = "update " + tableName + " set product_quantity = " + productQuantity + ", enabled = " + state + ", updated_at = '" + date + "' where id = " + productId + ";";
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addProductToCart(Map<String, String> parameters)
    {
        IProductModel[] products = new IProductModel[1];
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
        try
        {
            orderController.addToOrder(user, products);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
