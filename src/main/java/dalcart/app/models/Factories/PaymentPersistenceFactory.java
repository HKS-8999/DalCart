package dalcart.app.models.Factories;

import dalcart.app.models.Repository.IPaymentPersistence;
import dalcart.app.models.Repository.PaymentDB;

public class PaymentPersistenceFactory implements IPaymentPersistenceFactory {

    @Override
    public IPaymentPersistence createIPaymentPersistence() {
        return new PaymentDB();
    }
}
