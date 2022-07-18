package dalcart.app.Repository;

import dalcart.app.models.IProductModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface IProductPersistence
{
    enum StorageResult
    {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }

    public StorageResult saveProduct();
    public StorageResult deleteProduct();
    public StorageResult updateProduct();

    public StorageResult addProductToCart(IProductModel productModel);
    public HashMap getProductDetails(IProductModel productModel);
}
