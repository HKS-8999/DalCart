package dalcart.app.models.Factories;

import dalcart.app.models.IProductModel;

public interface IProductModelFactory {
    IProductModel createProductModel();

    IProductModel createProductModel(String productName,
                                     Integer productId,
                                     String productDescription,
                                     Integer productPrice,
                                     Integer productQuantity,
                                     Boolean enabled,
                                     String productImage);
}
