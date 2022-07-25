package dalcart.app.Repository;

import dalcart.app.models.DeliveryInformationModel;

public interface IDeliveryInformationPersistence
{
    enum StorageResult
    {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }

    public StorageResult saveDeliveryInformation(DeliveryInformationModel deliveryInformationModel, Integer orderId);
}
