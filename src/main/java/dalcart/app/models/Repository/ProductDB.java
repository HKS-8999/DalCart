package dalcart.app.models.Repository;

import dalcart.app.models.Factories.IProductModelFactory;
import dalcart.app.models.Factories.ProductModelFactory;
import dalcart.app.models.IProductModel;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDB implements IProductPersistence
{
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String tableName = "products";
    public ProductDB()
    {
    }

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
                    IProductModelFactory productModelFactory = new ProductModelFactory();
                    IProductModel product = productModelFactory.createProductModel();
                    product.setProductId(resultSet.getInt(1));
                    product.setProductName(resultSet.getString(2));
                    product.setProductDescription(resultSet.getString(3));
                    product.setProductPrice(resultSet.getInt(4));
                    product.setProductQuantity(resultSet.getInt(5));
                    product.setProductImage(resultSet.getString(6));
                    product.setEnabled(resultSet.getBoolean(7));
                    product_detail.add(product);
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
                    IProductModelFactory productModelFactory = new ProductModelFactory();
                    IProductModel product = productModelFactory.createProductModel();
                    product.setProductId(resultSet.getInt(1));
                    product.setProductName(resultSet.getString(2));
                    product.setProductDescription(resultSet.getString(3));
                    product.setProductPrice(resultSet.getInt(4));
                    product.setProductQuantity(resultSet.getInt(5));
                    product.setProductImage(resultSet.getString(6));
                    product_detail.add(product);
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
            ArrayList<IProductModel> product_detail = new ArrayList<>();
            try
            {
                String query = "select * from " + tableName + " where product_name like '%" + keyword + "%' and enabled = 1 ;";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    IProductModelFactory productModelFactory = new ProductModelFactory();
                    IProductModel product = productModelFactory.createProductModel();
                    product.setProductId(resultSet.getInt(1));
                    product.setProductName(resultSet.getString(2));
                    product.setProductDescription(resultSet.getString(3));
                    product.setProductPrice(resultSet.getInt(4));
                    product.setProductQuantity(resultSet.getInt(5));
                    product.setProductImage(resultSet.getString(6));
                    product_detail.add(product);
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
        try
        {
            String query = "select * from " + tableName + " where id = " + productId + ";";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            IProductModelFactory productModelFactory = new ProductModelFactory();
            IProductModel product = productModelFactory.createProductModel();
            while (resultSet.next())
            {
                product.setProductId(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setProductDescription(resultSet.getString(3));
                product.setProductPrice(resultSet.getInt(4));
                product.setProductQuantity(resultSet.getInt(5));
                product.setProductImage(resultSet.getString(6));
                product.setEnabled(resultSet.getBoolean(7));
            }
            return product;
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
            return productId;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public StorageResult saveProduct(IProductModel product)
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
            return StorageResult.STORAGE_SUCCESS;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }
    }

    public StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState)
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
            return StorageResult.STORAGE_SUCCESS;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }
    }

    public Integer getProductQuantity(Integer productId)
    {
        Integer quantity = 0;
        try
        {
            String query = "select product_quantity from " + tableName + " where id = " + productId + ";";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next())
            {
                quantity = resultSet.getInt(1);
            }
            return quantity;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Integer getTotalOfProducts(HashMap<Integer, Integer> products)
    {
        try
        {
            Integer total = 0;
            for (Map.Entry<Integer, Integer> val : products.entrySet())
            {
                Integer id = val.getKey();
                String query = "select product_price from " + tableName + " where id = " + id + ";";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    total += (resultSet.getInt(1))* val.getValue();
                }
            }
            return total;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteProduct(Integer productId)
    {
        try
        {
            String query = "delete from " + tableName + " where id = " + productId + ";";
            statement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            statement.executeUpdate(query);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean decreaseProductQuantity(HashMap<Integer, Integer> products)
    {
        try
        {
            for (Map.Entry<Integer, Integer> val : products.entrySet())
            {
                Integer id = val.getKey();
                Integer quantity = 0;
                String query = "select product_quantity from " + tableName + " where id = " + id + ";";
                preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
                resultSet = preparedStatement.executeQuery(query);
                while(resultSet.next())
                {
                    quantity = resultSet.getInt(1);
                    quantity -= val.getValue();
                }
                String query2 = "update " + tableName + " set product_quantity = " + quantity + " where id = " + id + ";";
                statement = ConnectionManager.getInstance().getConnection().prepareStatement(query2);
                statement.executeUpdate(query2);
            }
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
