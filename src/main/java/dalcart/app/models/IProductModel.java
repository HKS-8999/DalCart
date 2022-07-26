package dalcart.app.models;

import dalcart.app.models.Repository.IProductPersistence;

import java.util.ArrayList;
import java.util.HashMap;

public interface IProductModel {
    Integer getProductId();

    void setProductId(Integer productId);

    String getProductName();

    void setProductName(String productName);

    String getProductDescription();

    void setProductDescription(String productDescription);

    Integer getProductPrice();

    void setProductPrice(Integer productPrice);

    Integer getProductQuantity();

    void setProductQuantity(Integer productQuantity);

    Boolean getEnabled();

    void setEnabled(Boolean productState);

    String getProductImage();

    void setProductImage(String productImage);

    ArrayList<IProductModel> getProductsToDisplay(String searchWord, IProductPersistence productDB);

    ArrayList<IProductModel> getProducts(IProductPersistence productDB);

    IProductPersistence.StorageResult saveProduct(IProductModel product, IProductPersistence productDB);

    IProductPersistence.StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState, IProductPersistence productDB);

    Integer getLastProductId(IProductPersistence productDB);

    IProductModel getProductById(Integer productId, IProductPersistence productDB);

    Integer getProductQuantity(IProductPersistence productDB, Integer productQuantity);

    Integer getTotalOfProducts(IProductPersistence productDB, HashMap<Integer, Integer> products);

    Boolean decreaseProductQuantity(IProductPersistence productDB, HashMap<Integer, Integer> products);
}
