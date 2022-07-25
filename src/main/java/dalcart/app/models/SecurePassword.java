package dalcart.app.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurePassword implements ISecurePassword
{
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public IUser encrypt(IUser user)
    {
       String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
       user.setPassword(encodedPassword);
       return user;
    }
}
