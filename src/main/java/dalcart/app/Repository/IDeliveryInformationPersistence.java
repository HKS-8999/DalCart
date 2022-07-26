package dalcart.app.Repository;

import dalcart.app.models.DeliveryInformationModel;

public interface IDeliveryInformationPersistence
{
    enum StorageResult
    {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }
    StorageResult saveDeliveryInformation(DeliveryInformationModel deliveryInformationModel, Integer orderId);
}
