package dalcart.app.models;

import dalcart.app.items.Product;

import java.util.ArrayList;
import java.util.Map;

public interface IProductModel
{
    public ArrayList getProductDetails(Product product);
    public String addProductToCart(Map<String,String> allParams);
}
