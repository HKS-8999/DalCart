package dalcart.app.Repository;

import dalcart.app.models.IProductModel;
import java.util.ArrayList;
import java.util.HashMap;

public interface IProductPersistence
{
    enum StorageResult
    {
        STORAGE_FAILURE,
        STORAGE_SUCCESS
    }

    public ArrayList getProductDetails();
    public ArrayList getProductDetailsForDisplay(String keyword);
    public IProductModel getProductById(Integer productId);
    public Integer getLastProductId();
    public StorageResult saveProduct(IProductModel product);
    public StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState);
    public Integer getProductQuantity(Integer productId);
    public Integer getTotalOfProducts(HashMap<Integer, Integer> products);
    public Boolean deleteProduct(Integer productId);
    public Boolean decreaseProductQuantity(HashMap<Integer, Integer> products);
}
