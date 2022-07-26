package dalcart.app.models;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class PaymentInformationValidator
{
    private DateTimeFormatter dateFormatter;
    public boolean isCardNumberValid(PaymentModel paymentModel)
    {
        String regex = "(?<!\\d)\\d{16}(?!\\d)";
        return Pattern.compile(regex).matcher(paymentModel.getCardNumber()).matches();
    }

    public boolean isCartCvvValid(PaymentModel paymentModel)
    {
        String regex = "^[0-9]{3}$";
        return Pattern.compile(regex).matcher(paymentModel.getCardCVV()).matches();
    }

    public boolean isExpiryDateValid(PaymentModel paymentModel)
    {
        return true;
    }

    public boolean validatePaymentInformation(PaymentModel paymentModel)
    {
        if(isCardNumberValid(paymentModel) && isCartCvvValid(paymentModel) && isExpiryDateValid(paymentModel))
        {
            return true;
        }
        return false;
    }
}
