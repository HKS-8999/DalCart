package dalcart.app.models;

import dalcart.app.models.Repository.OrderDB;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class OrderModelTest {

    @Test
    public void getOrderSuccessTest()
    {
        ArrayList<IProductModel> product_detail;
        IOrderModel order = new OrderModelMock();
        order.setOrderId(999);
        Assertions.assertEquals(999, order.getOrderId());
    }
    @Test
    public void getOrderByUserIdTest() {
        IOrderModel retrivedOrder = OrderDB.findByUserId(-1);
        Assertions.assertEquals(null, retrivedOrder);
        retrivedOrder = OrderDB.findByUserId(-2);
        Assertions.assertEquals(null, retrivedOrder);
        retrivedOrder = OrderDB.findByUserId(0);
        Assertions.assertEquals(null, retrivedOrder);
    }
}
