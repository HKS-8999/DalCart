package dalcart.app.service;

import dalcart.app.Repository.ProductDB;
import dalcart.app.models.IProductPersistence;
import dalcart.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ProductService
{

    @Autowired
    public IProductService iProductService;
    public Product p;
    public ArrayList getProducts()
    {
        ArrayList<Product> productDetail = new ArrayList<>();
        productDetail = iProductService.getProductDetails(p);
        return View(productDetail);
    }

}
