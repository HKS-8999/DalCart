package dalcart.app.models.Factories;

import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModel;

public class ProductModelFactory implements IProductModelFactory {

    @Override
    public IProductModel createProductModel() {
        return new ProductModel();
    }

    @Override
    public IProductModel createProductModel(String productName, Integer productId, String productDescription, Integer productPrice, Integer productQuantity, Boolean enabled, String productImage) {
        return new ProductModel(productName, productId, productDescription, productPrice, productQuantity, enabled, productImage);
    }
}
