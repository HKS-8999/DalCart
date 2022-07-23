package dalcart.app.Factories;

import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;

public class ProductModelFactory implements IProductModelFactory
{

    @Override
    public IProductModel createProductModel() {
        return new ProductModel();
    }
}
