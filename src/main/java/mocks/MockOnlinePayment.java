package mocks;
import dalcart.app.models.IOnlinePayment;
import java.lang.String;
public class MockOnlinePayment implements IOnlinePayment {
    public static String cardNum;
    public static String cvv;
    public static String exp;

    public static void main(String[] args) {

        String[] cardOne = {"1234567890","112","10/22"};
        String[] cardTwo = {"0987654321","211","12/23"};

        cardNum = cardOne[0];
        cvv = cardOne[1];
        exp = cardOne[2];
        cardNum = cardTwo[0];
        cvv = cardTwo[1];
        exp = cardTwo[2];

        MockOnlinePayment op = new MockOnlinePayment();
        op.makePayment(cardNum,cvv,exp);
    }

    @Override
    public String makePayment(String cardNumber, String cVV, String Expiry) {
        if (cardNumber.equals("1234567890") && cVV.equals("112") && Expiry.equals("10/22"))
        {
            System.out.print("This is a Valid card");
            return "abcd";
        }
        else if(cardNum.equals("0987654321") && cvv.equals("211") && exp.equals("12/23"))
        {
            System.out.print("This is a Valid card");
            return "wxyz";
        }
        else
        {
            System.out.print("The card details doesnot match");
            return "pqrs";
        }
    }
}
