package dalcart.app.service;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.Product;

import java.util.ArrayList;

public interface IProductService
{
    public ArrayList getProductDetails(Product product);
    IProductPersistence.StorageResult addProductToCart(Product product);
}
