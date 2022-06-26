package dalcart.app.service;

import dalcart.app.models.IProductPersistence;
import dalcart.app.models.IUserPersistence;
import dalcart.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductService
{

    @Autowired
    private IProductPersistence iProductPersistence;

    public HashMap<String,String> getProductDetails()
    {
        HashMap<String,String> productDetail = new HashMap<>();
        productDetail = iProductPersistence.getProductDetails(Product product);
        return productDetail;
    }
}
