package dalcart.app.Factories;

import dalcart.app.Repository.DeliveryInformationDB;
import dalcart.app.Repository.IDeliveryInformationPersistence;

public class DeliveryInformationAddressPersistenceFactory implements IDeliveryAddressPersistenceFactory
{
    @Override
    public IDeliveryInformationPersistence createIDeliveryInformationPersistence()
    {
        return new DeliveryInformationDB();
    }
}
