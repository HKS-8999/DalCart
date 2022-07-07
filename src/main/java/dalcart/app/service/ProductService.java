package dalcart.app.service;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ProductService
{

    @Autowired
    public IProductService iProductService;

    public Product p;
    public ArrayList getProducts()
    {
        ArrayList<Product> productDetail;
        productDetail = iProductService.getProductDetails(p);
        return productDetail;
    }

    public String addToCart(Map<String,String> allParams)
    {
        return iProductService.addProductToCart(allParams);
    }

}
