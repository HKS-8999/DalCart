package dalcart.app.models;
import dalcart.app.models.Factories.IPaymentModelFactory;
import dalcart.app.models.Factories.PaymentModelFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentValidationTest {
    PaymentInformationValidator PaymentValidate = new PaymentInformationValidator();
    IPaymentModelFactory factory = new PaymentModelFactory();

    @Test
    public void isCardNumberValidSuccessTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardNumber("1234567890987654");
        Assertions.assertEquals(true, PaymentValidate.isCardNumberValid(paymentmodel));
    }

    @Test
    public void isCardNumberValidFailureTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardNumber("123456789098765");
        Assertions.assertEquals(false, PaymentValidate.isCardNumberValid(paymentmodel));
    }

    @Test
    public void isCVVValidSuccessTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardCVV("123");
        Assertions.assertEquals(true, PaymentValidate.isCartCvvValid(paymentmodel));
    }

    @Test
    public void isCVVValidFailureTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardCVV("1234");
        Assertions.assertEquals(false, PaymentValidate.isCartCvvValid(paymentmodel));
    }

    @Test
    public void isExpiryDateValidSuccessTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setExpiryDate("12/2023");
        Assertions.assertEquals(true, PaymentValidate.isExpiryDateValid(paymentmodel));
    }

    @Test
    public void isExpiryDateValidFailureTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setExpiryDate("14/1999");
        Assertions.assertEquals(true, PaymentValidate.isExpiryDateValid(paymentmodel));
    }

    @Test
    public void isCardDetailsValidTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardNumber("1234567890987654");
        paymentmodel.setCardCVV("123");
        paymentmodel.setExpiryDate("12/2023");
        Assertions.assertEquals(true, PaymentValidate.validatePaymentInformation(paymentmodel));
    }

    @Test
    public void isCardDetailsNotValidTest() {
        PaymentModel paymentmodel = factory.createPaymentModel();
        paymentmodel.setCardNumber("123456789098765");
        paymentmodel.setCardCVV("1234");
        paymentmodel.setExpiryDate("12/2023");
        Assertions.assertEquals(false, PaymentValidate.validatePaymentInformation(paymentmodel));
    }
}
