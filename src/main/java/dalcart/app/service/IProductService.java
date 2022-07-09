package dalcart.app.service;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.Product;

import java.util.ArrayList;
import java.util.Map;

public interface IProductService
{
    public ArrayList getProductDetails(Product product);
    public String addProductToCart(Map<String,String> allParams);
}
