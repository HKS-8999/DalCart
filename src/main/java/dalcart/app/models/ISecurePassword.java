package dalcart.app.models;

public interface ISecurePassword {
    IUser encrypt(IUser user);
}