package dalcart.app.Factories;

import dalcart.app.Repository.IDeliveryInformationPersistence;

public interface IDeliveryAddressPersistenceFactory
{
    public IDeliveryInformationPersistence createIDeliveryInformationPersistence();
}
