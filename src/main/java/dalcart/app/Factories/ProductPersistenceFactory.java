package dalcart.app.Factories;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.Repository.ProductDB;

public class ProductPersistenceFactory implements IProductPersistenceFactory
{

    @Override
    public IProductPersistence createIProductPersistence()
    {
        return new ProductDB();
    }
}
