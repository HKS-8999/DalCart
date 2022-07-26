package dalcart.app.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurePassword implements ISecurePassword {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public IUser encrypt(IUser user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return user;
    }
}
