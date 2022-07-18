package dalcart.app.models;

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


    public ArrayList<IProductModel> getProductsToDisplay(String searchWord, IProductDB productDB);
    public ArrayList<IProductModel> getProducts(IProductDB productDB);
    public void addProductToCart(Map<String,String> parameters, IProductDB productDB);
    public void saveProduct(IProductModel product, IProductDB productDB);
    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState, IProductDB productDB);
    public Integer getLastProductId(IProductDB productDB);
    public IProductModel getProductById(Integer productId, IProductDB productDB);
}
