package dalcart.app.models.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentDB implements IPaymentPersistence
{
    private PreparedStatement preparedStatement;
    private String tableName = "payment";
    @Override
    public StorageResult savePaymentInformation(Integer orderId, Integer userId, Integer total)
    {
        try
        {
            LocalDate date = java.time.LocalDate.now();
            long token = (long) (Math.random() * 10000000000000000L);
            String query = "insert into " + tableName + " (order_id, order_total, payment_token, user_id, payment_date) values (?, ?, ?, ?, ?)";
            preparedStatement= ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, total);
            preparedStatement.setString(3, String.valueOf(token));
            preparedStatement.setInt(4, userId);
            preparedStatement.setString(5, String.valueOf(date));
            preparedStatement.executeUpdate();
            return IPaymentPersistence.StorageResult.STORAGE_SUCCESS;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return IPaymentPersistence.StorageResult.STORAGE_FAILURE;
        }
    }
}
