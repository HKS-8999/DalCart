package dalcart.app.Factories;

import dalcart.app.models.IProductModel;

public interface IProductModelFactory
{
    public IProductModel createProductModel();
    public IProductModel createProductModel(String productName,
                                            Integer productId,
                                            String productDescription,
                                            Integer productPrice,
                                            Integer productQuantity,
                                            Boolean enabled,
                                            String productImage);
}
