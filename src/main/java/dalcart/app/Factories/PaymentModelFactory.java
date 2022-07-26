package dalcart.app.Factories;

import dalcart.app.models.PaymentModel;

public class PaymentModelFactory implements IPaymentModelFactory
{
    @Override
    public PaymentModel createPaymentModel(String cardNumber, String cardCVV, String expiryDate) {
        return new PaymentModel(cardNumber, cardCVV, expiryDate);
    }
}
