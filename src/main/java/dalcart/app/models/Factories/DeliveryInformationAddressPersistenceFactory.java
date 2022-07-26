package dalcart.app.models.Factories;

import dalcart.app.models.Repository.DeliveryInformationDB;
import dalcart.app.models.Repository.IDeliveryInformationPersistence;

public class DeliveryInformationAddressPersistenceFactory implements IDeliveryAddressPersistenceFactory
{
    @Override
    public IDeliveryInformationPersistence createIDeliveryInformationPersistence()
    {
        return new DeliveryInformationDB();
    }
}
