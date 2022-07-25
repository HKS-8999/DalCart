package dalcart.app.Repository;

import dalcart.app.models.DeliveryInformationModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DeliveryInformationDB implements IDeliveryInformationPersistence
{
    PreparedStatement preparedStatement;
    String tableName = "delivery_information";

    @Override
    public StorageResult saveDeliveryInformation(DeliveryInformationModel deliveryInformationModel)
    {
        try
        {
            LocalDate date = java.time.LocalDate.now();
            String query = "insert into " + tableName + " (name, email, mobile_number, address, created_date) values (?, ?, ?, ?, ?)";
            preparedStatement= ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, deliveryInformationModel.getName());
            preparedStatement.setString(2, deliveryInformationModel.getEmail());
            preparedStatement.setString(3, deliveryInformationModel.getMobileNumber());
            preparedStatement.setString(4, deliveryInformationModel.getAddress());
            preparedStatement.setString(5, String.valueOf(date));
            preparedStatement.executeUpdate();
            return StorageResult.STORAGE_SUCCESS;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }
    }
}