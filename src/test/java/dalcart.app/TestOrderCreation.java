package dalcart.app;

import dalcart.app.items.IProduct;
import dalcart.app.items.Product;
import dalcart.app.models.IOrderModel;
import dalcart.app.models.IUserModel;
import dalcart.app.models.OrderModel;
import dalcart.app.utils.OrderUtils;
import mocks.MockProduct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
class TestingApplicationTests {

    @Test
    void makeIdempotentOrderTransaction() {
        IOrderModel order = new OrderModel();
        order.setUserId(1);
        order.setState(OrderUtils.getState("address"));
        Integer orderId = order.save();
        order.delete();
        //System.out.println("**************" + orderId + "***************");
        assertNotNull(orderId);
    }

    public static void main(){
        new TestingApplicationTests().makeIdempotentOrderTransaction();
    }

}
