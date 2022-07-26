package dalcart.app.models;

import java.util.regex.Pattern;

public class PaymentInformationValidator {

    public boolean isCardNumberValid(PaymentModel paymentModel) {
        String regex = "(?<!\\d)\\d{16}(?!\\d)";
        return Pattern.compile(regex).matcher(paymentModel.getCardNumber()).matches();
    }

    public boolean isCartCvvValid(PaymentModel paymentModel) {
        String regex = "^[0-9]{3}$";
        return Pattern.compile(regex).matcher(paymentModel.getCardCVV()).matches();
    }

    public boolean isExpiryDateValid(PaymentModel paymentModel) {
        return true;
    }

    public boolean validatePaymentInformation(PaymentModel paymentModel) {
        return isCardNumberValid(paymentModel) && isCartCvvValid(paymentModel) && isExpiryDateValid(paymentModel);
    }
}
