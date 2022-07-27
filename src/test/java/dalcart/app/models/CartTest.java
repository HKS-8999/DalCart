package dalcart.app.models;

import dalcart.app.repository.OrderProductsDBMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class CartTest {
    @Test
    public void getProductsOfUserSuccessTest() {
        HashMap<Integer, Integer> products = new HashMap<>();
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        products = orderProductsDBMock.getProductsOfUser(1);
        Assertions.assertEquals(2, products.size());
    }

    @Test
    public void getProductsOfUserFailureTest() {
        HashMap<Integer, Integer> products = new HashMap<>();
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        products = orderProductsDBMock.getProductsOfUser(2);
        Assertions.assertEquals(null, products);
    }

    @Test
    public void addProductToCartSuccessTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        Integer i = orderProductsDBMock.addProductToCart(2, 1);
        Assertions.assertEquals(1, i);
    }

    @Test
    public void addProductToCartFailureTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        Integer i = orderProductsDBMock.addProductToCart(3, 1);
        Assertions.assertEquals(0, i);
    }

    @Test
    public void addProductToCartAlreadyInCartSuccessTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        Integer i = orderProductsDBMock.addProductToCartAlreadyInCart(2, 1);
        Assertions.assertEquals(2, i);
    }

    @Test
    public void addProductToCartAlreadyInCartFailureTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        Integer i = orderProductsDBMock.addProductToCartAlreadyInCart(3, 1);
        Assertions.assertEquals(1, i);
    }

    @Test
    public void increaseQuantityOfProductInCartSuccessTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.increaseProductQuantity(5, 2, 190);
        Assertions.assertEquals(true, b);
    }

    @Test
    public void increaseQuantityOfProductInCartFailureTest() {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.increaseProductQuantity(6, 2, 190);
        Assertions.assertEquals(false, b);
    }

    @Test
    public void decreaseQuantityOfProductInCartSuccessTest()
    {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.decreaseProductQuantity(5, 2, 190);
        Assertions.assertEquals(true, b);
    }

    @Test
    public void decreaseQuantityOfProductInCartFailureTest()
    {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.decreaseProductQuantity(6, 2, 190);
        Assertions.assertEquals(false, b);
    }

    @Test
    public void removeItemFromCartSuccessTest()
    {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.removeProductFromCart(1, 120);
        Assertions.assertEquals(true, b);
    }

    @Test
    public void removeItemFromCartFailureTest()
    {
        OrderProductsDBMock orderProductsDBMock = new OrderProductsDBMock();
        boolean b = orderProductsDBMock.removeProductFromCart(2, 120);
        Assertions.assertEquals(false, b);
    }
}
