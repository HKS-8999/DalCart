package dalcart.app.models.Repository;

import dalcart.app.models.IProductModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface IProductPersistence {
    enum StorageResult {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }
     ArrayList getProductDetails();

     ArrayList getProductDetailsForDisplay(String keyword);

     IProductModel getProductById(Integer productId);

     Integer getLastProductId();

     StorageResult saveProduct(IProductModel product);

     StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState);

     Integer getProductQuantity(Integer productId);

     Integer getTotalOfProducts(HashMap<Integer, Integer> products);

    Boolean deleteProduct(Integer productId);

     Boolean decreaseProductQuantity(HashMap<Integer, Integer> products);
}
