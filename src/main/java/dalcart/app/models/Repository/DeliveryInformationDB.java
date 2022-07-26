package dalcart.app.models.Repository;

import dalcart.app.models.DeliveryInformationModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DeliveryInformationDB implements IDeliveryInformationPersistence {
    private PreparedStatement preparedStatement;
    private String tableName = "delivery_information";

    @Override
    public StorageResult saveDeliveryInformation(DeliveryInformationModel deliveryInformationModel, Integer orderId) {
        try {
            LocalDate date = java.time.LocalDate.now();
            String query = "insert into " + tableName + " (name, email, mobile_number, address, created_date, order_id) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, deliveryInformationModel.getName());
            preparedStatement.setString(2, deliveryInformationModel.getEmail());
            preparedStatement.setString(3, deliveryInformationModel.getMobileNumber());
            preparedStatement.setString(4, deliveryInformationModel.getAddress());
            preparedStatement.setString(5, String.valueOf(date));
            preparedStatement.setInt(6, orderId);
            preparedStatement.executeUpdate();
            return StorageResult.STORAGE_SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return StorageResult.STORAGE_FAILURE;
        }
    }
}
