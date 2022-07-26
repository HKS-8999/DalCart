package dalcart.app.Factories;

import dalcart.app.Repository.IPaymentPersistence;
import dalcart.app.Repository.PaymentDB;

public class PaymentPersistenceFactory implements IPaymentPersistenceFactory {

    @Override
    public IPaymentPersistence createIPaymentPersistence() {
        return new PaymentDB();
    }
}
