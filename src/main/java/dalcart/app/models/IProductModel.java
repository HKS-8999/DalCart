package dalcart.app.models;

import dalcart.app.Repository.IProductPersistence;

import java.util.ArrayList;
import java.util.Map;

public interface IProductModel
{
    public void setProductId(Integer productId);
    public Integer getProductId();
    public void setProductName(String productName);
    public String getProductName();
    public void setProductDescription(String productDescription);
    public String getProductDescription();
    public void setProductPrice(Integer productPrice);
    public Integer getProductPrice();
    public void setProductQuantity(Integer productQuantity);
    public Integer getProductQuantity();
    public void setEnabled(Boolean productState);
    public Boolean getEnabled();
    public void setProductImage(String productImage);
    public String getProductImage();


    public ArrayList<IProductModel> getProductsToDisplay(String searchWord, IProductPersistence productDB);
    public ArrayList<IProductModel> getProducts(IProductPersistence productDB);
    public IProductPersistence.StorageResult addProductToCart(Map<String,String> parameters, IProductPersistence productDB, Integer userId);
    public IProductPersistence.StorageResult saveProduct(IProductModel product, IProductPersistence productDB);
    public IProductPersistence.StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState, IProductPersistence productDB);
    public Integer getLastProductId(IProductPersistence productDB);
    public IProductModel getProductById(Integer productId, IProductPersistence productDB);
}
