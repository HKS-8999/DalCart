package dalcart.app.Repository;

public interface IPaymentPersistence
{
    enum StorageResult
    {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }
    StorageResult savePaymentInformation(Integer orderId, Integer userId, Integer total);
}
