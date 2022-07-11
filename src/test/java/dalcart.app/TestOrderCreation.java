package dalcart.app;

import dalcart.app.database.ConnectionManager;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.OrderModel;
import dalcart.app.utils.OrderUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.Assert.*;

@SpringBootTest
class TestingApplicationTests {

    @Test
    void makeIdempotentOrderTransaction() throws SQLException {

        ConnectionManager connectionManager = ConnectionManager.getInstance();
        connectionManager.begin();
        IOrderModel order = new OrderModel();
        order.setUserId(1);
        order.setState(OrderUtils.getState("address"));
        //System.out.println("**************TESTING***************");
        Integer orderId = order.save();
        order.delete();
        //System.out.println("**************" + orderId + "***************");
        connectionManager.commit();
        assertNotNull(orderId);
    }

}
