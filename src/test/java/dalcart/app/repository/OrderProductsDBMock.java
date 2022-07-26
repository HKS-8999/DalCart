package dalcart.app.repository;

import java.util.HashMap;

public class OrderProductsDBMock {
    public HashMap<Integer, Integer> getProductsOfUser(Integer userId) {
        HashMap<Integer, Integer> products = new HashMap<>();
        if (userId == 1) {
            products.put(1, 2);
            products.put(2, 5);
            return products;
        } else {
            return null;
        }
    }

    public Integer addProductToCart(Integer productId, Integer userId) {
        HashMap<Integer, Integer> products = new HashMap<>();
        if (userId == 1 && productId == 2) {
            products.put(2, 1);
            return products.get(2);
        }
        return 0;
    }

    public Integer addProductToCartAlreadyInCart(Integer productId, Integer userId) {
        HashMap<Integer, Integer> products = new HashMap<>();
        products.put(2, 1);
        if (userId == 1 && productId == 2) {
            products.put(2, products.get(2) + 1);
            return products.get(2);
        }
        return 1;
    }

    public boolean increaseProductQuantity(Integer productQuantity, Integer productId, Integer orderId) {
        Integer quantity = productQuantity;
        if (productQuantity == 5 && productId == 2 && orderId == 190) {
            quantity = productQuantity + 1;
        }
        if (productQuantity == 6 && productId == 2 && orderId == 190) {

        }
        if (quantity == productQuantity + 1) {
            return true;
        }
        return false;
    }


}
