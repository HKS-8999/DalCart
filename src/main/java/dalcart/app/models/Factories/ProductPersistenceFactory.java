package dalcart.app.models.Factories;

import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.Repository.ProductDB;

public class ProductPersistenceFactory implements IProductPersistenceFactory {

    @Override
    public IProductPersistence createIProductPersistence() {
        return new ProductDB();
    }
}
