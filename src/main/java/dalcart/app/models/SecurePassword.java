package dalcart.app.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurePassword implements ISecurePassword
{
    public IUser encrypt(IUser user)
    {
        /*
        Reference: https://subscription.packtpub.com/book/security/9781849697767/1/ch01lvl1sec09/creating-a-strong-hash-simple
         */
        String password = user.getPassword();
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes());
            byte byteData[] = messageDigest.digest();
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < byteData.length; i++)
            {
                str.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            user.setPassword(str.toString());
            return user;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
