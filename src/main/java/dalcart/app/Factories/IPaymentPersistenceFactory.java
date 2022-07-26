package dalcart.app.Factories;

import dalcart.app.Repository.IPaymentPersistence;

public interface IPaymentPersistenceFactory
{
    IPaymentPersistence createIPaymentPersistence();
}
