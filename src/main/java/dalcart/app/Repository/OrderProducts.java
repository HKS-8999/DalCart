package dalcart.app.Repository;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.IProductPersistenceFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Factories.ProductPersistenceFactory;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IProductModel;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.HashMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Repository
public
class OrderProducts  {

    static PreparedStatement preparedStatement;
    static Statement statement;
    static ResultSet resultSet;
    IProductPersistenceFactory productPersistenceFactory = new ProductPersistenceFactory();
    IProductPersistence productDB = productPersistenceFactory.createIProductPersistence();

    IProductModelFactory productModelFactory = new ProductModelFactory();
    IProductModel productModel = productModelFactory.createProductModel();

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
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HashMap<Integer, Integer> getProductsOfUser(Integer userId)
    {
        OrderDB orderDB = new OrderDB();
        IOrderModel order = orderDB.findOrderInCartByUserId(userId);
        Integer orderId = order.getOrderId();
        HashMap<Integer, Integer> products = new HashMap<>();
        String query = "select product_id,product_quantity from order_products where order_id = " + orderId + ";";
        try
        {
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next())
            {
                products.put(resultSet.getInt(1), resultSet.getInt(2));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return products;
    }
    public boolean increaseProductQuantity(Integer productId, Integer productQuantity, Integer orderId)
    {
        Integer updatedQuantity = productQuantity + 1;
        Integer availableProduct = productModel.getProductQuantity(productDB,productId);
        if(availableProduct > 0)
        {
            String query = "update order_products set product_quantity = " + updatedQuantity + " where order_id = " + orderId + " and product_id = " + productId + ";";
            try
            {
                statement = ConnectionManager.getInstance().getConnection().createStatement();
                statement.executeUpdate(query);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean decreaseProductQuantity(Integer productQuantity)
    {
        Integer updatedQuantity = productQuantity - 1;
        String query = "update order_products set product_quantity = " + updatedQuantity + ";";
        try
        {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean removeProductFromCart(Integer productId, Integer orderId)
//    {
//
//    }
}
