package dalcart.app.models;

public interface Model {
    Integer save();

    IOrderModel last();

    boolean delete();
}