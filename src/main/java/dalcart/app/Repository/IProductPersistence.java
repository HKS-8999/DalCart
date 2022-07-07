package dalcart.app.Repository;

import dalcart.app.models.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface IProductPersistence
{
    enum StorageResult
    {
        ALREADY_IN_CART,
        STORAGE_FAILURE,
        SUCCESS
    }

    public StorageResult addProductToCart(Product product);
    public HashMap getProductDetails(Product product);
}
