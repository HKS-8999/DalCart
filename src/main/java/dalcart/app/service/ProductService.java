package dalcart.app.service;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService
{

    @Autowired
    public IProductService iProductService;

    @Autowired
    public IProductPersistence iProductPersistence;
    public Product p;
    public ArrayList getProducts()
    {
        ArrayList<Product> productDetail = new ArrayList<>();
        productDetail = iProductService.getProductDetails(p);
        return productDetail;
    }

    public IProductPersistence.StorageResult addToCart(Product product)
    {
        return iProductPersistence.addProductToCart(p);
    }

}
