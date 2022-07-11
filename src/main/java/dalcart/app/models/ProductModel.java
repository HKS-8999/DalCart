package dalcart.app.models;

import dalcart.app.items.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ProductModel
{

    @Autowired
    public IProductModel iProductModel;

    public Product p;
    public ArrayList getProducts()
    {
        ArrayList<Product> productDetail;
        productDetail = iProductModel.getProductDetails(p);
        return productDetail;
    }

    public String addToCart(Map<String,String> allParams)
    {
        return iProductModel.addProductToCart(allParams);
    }

}
