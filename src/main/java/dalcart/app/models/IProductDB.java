package dalcart.app.models;

import java.util.ArrayList;
import java.util.Map;

public interface IProductDB
{
    public ArrayList getProductDetails();
    public ArrayList getProductDetailsForDisplay(String keyword);
    public IProductModel getProductById(Integer productId);
    public Integer getLastProductId();
    public void saveProduct(IProductModel product);
    public void updateProduct(Integer productId, Integer productQuantity, Boolean productState);
    public void addProductToCart(Map<String, String> parameters);

}
