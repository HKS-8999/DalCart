package dalcart.app.models;

import java.sql.SQLException;

public interface Model
{
    public Integer save();
    public IOrderModel last();
    public boolean delete();
}