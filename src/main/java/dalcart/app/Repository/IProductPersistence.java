package dalcart.app.Repository;

import dalcart.app.models.IProductModel;

import java.util.ArrayList;
import java.util.Map;


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
    public void saveProduct(IProductModel product);
    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState);
    public void addProductToCart(Map<String, String> parameters, Integer userId);

//    public StorageResult saveProduct();
//    public StorageResult deleteProduct();
//    public StorageResult updateProduct();

//    public StorageResult addProductToCart(Product product);
//    public HashMap getProductDetails(Product product);
}
