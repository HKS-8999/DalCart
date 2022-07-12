package dalcart.app.models;

import dalcart.app.items.Product;

import java.util.ArrayList;
import java.util.Map;

public interface IProductModel
{
    enum productState
    {
        ENABLED,
        DISABLED
    }

    public void setProductId(Integer productId);
    public Integer getProductId();
    public void setProductName(String productName);
    public String getProductName();
    public void setProductDescription(String productDescription);
    public String getProductDescription();
    public void setProductPrice(Integer productPrice);
    public Integer getProductPrice();
    public void setProductQuantity();
    public Integer getProductQuantity();
    public void setProductState(Boolean productState);
    public Boolean getProductState();
    public void setProductImage(String productImage);
    public String getProductImage();
//    public ArrayList getProductDetails(Product product);
//    public String addProductToCart(Map<String,String> allParams);
}
