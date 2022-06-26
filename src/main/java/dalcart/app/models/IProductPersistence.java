package dalcart.app.models;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface IProductPersistence
{
    public HashMap getProductDetails(Product product);
}
