package dalcart.app.Factories;

import dalcart.app.Repository.IProductPersistence;

public interface IProductPersistenceFactory {
    IProductPersistence createIProductPersistence();
}
