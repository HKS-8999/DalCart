package dalcart.app.models;

import dalcart.app.models.Factories.IProductModelFactory;
import dalcart.app.models.Factories.ProductModelFactory;
import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.repository.ProductDBMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class ProductModelTest {
    @Test
    public void setProductNameSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductName("T-shirt");
        Assertions.assertEquals("T-shirt", product.getProductName());
    }

    @Test
    public void setProductNameFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductName("T-shirt");
        product.setProductName("xyz");
        Assertions.assertEquals("xyz", product.getProductName());
    }

    @Test
    public void getProductNameSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductName("T-shirt");
        Assertions.assertEquals("T-shirt", product.getProductName());
    }

    @Test
    public void getProductNameFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductName("T-shirt");
        product.setProductName("xyz");
        Assertions.assertEquals("xyz", product.getProductName());
    }

    @Test
    public void setProductIdSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductId(1);
        Assertions.assertEquals(1, product.getProductId());
    }

    @Test
    public void setProductIdFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductId(1);
        product.setProductId(2);
        Assertions.assertEquals(2, product.getProductId());
    }

    @Test
    public void getProductIdSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductId(2);
        Assertions.assertEquals(2, product.getProductId());
    }

    @Test
    public void getProductIdFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductId(1);
        product.setProductId(2);
        Assertions.assertEquals(2, product.getProductId());
    }

    @Test
    public void setProductDescriptionSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductDescription("ABCD XYZ");
        Assertions.assertEquals("ABCD XYZ", product.getProductDescription());
    }

    @Test
    public void setProductDescriptionFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductDescription("ABCD XYZ");
        product.setProductDescription("abcd xyzw");
        Assertions.assertEquals("abcd xyzw", product.getProductDescription());
    }

    @Test
    public void getProductDescriptionSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductDescription("ABCD XYZ");
        Assertions.assertEquals("ABCD XYZ", product.getProductDescription());
    }

    @Test
    public void getProductDescriptionFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductDescription("ABCD XYZ");
        product.setProductDescription("abcd xyzw");
        Assertions.assertEquals("abcd xyzw", product.getProductDescription());
    }

    @Test
    public void setProductPriceSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductPrice(30);
        Assertions.assertEquals(30, product.getProductPrice());
    }

    @Test
    public void setProductPriceFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductPrice(20);
        product.setProductPrice(30);
        Assertions.assertEquals(30, product.getProductPrice());
    }

    @Test
    public void getProductPriceSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductPrice(30);
        Assertions.assertEquals(30, product.getProductPrice());
    }

    @Test
    public void getProductPriceFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductPrice(20);
        product.setProductPrice(30);
        Assertions.assertEquals(30, product.getProductPrice());
    }

    @Test
    public void setProductQuantitySuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductQuantity(100);
        Assertions.assertEquals(100, product.getProductQuantity());
    }

    @Test
    public void setProductQuantityFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductQuantity(200);
        product.setProductQuantity(100);
        Assertions.assertEquals(100, product.getProductQuantity());
    }

    @Test
    public void setEnabledSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setEnabled(true);
        Assertions.assertEquals(true, product.getEnabled());
    }

    @Test
    public void setEnabledFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setEnabled(false);
        product.setEnabled(true);
        Assertions.assertEquals(true, product.getEnabled());
    }

    @Test
    public void getEnabledSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setEnabled(true);
        Assertions.assertEquals(true, product.getEnabled());
    }

    @Test
    public void getEnabledFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setEnabled(false);
        product.setEnabled(true);
        Assertions.assertEquals(true, product.getEnabled());
    }

    @Test
    public void setProductImageSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductImage("image.jpg");
        Assertions.assertEquals("image.jpg", product.getProductImage());
    }

    @Test
    public void setProductImageFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductImage("product.jpg");
        product.setProductImage("image.jpg");
        Assertions.assertEquals("image.jpg", product.getProductImage());
    }

    @Test
    public void getProductImageSuccessTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductImage("image.jpg");
        Assertions.assertEquals("image.jpg", product.getProductImage());
    }

    @Test
    public void getProductImageFailureTest() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel();
        product.setProductImage("product.jpg");
        product.setProductImage("image.jpg");
        Assertions.assertEquals("image.jpg", product.getProductImage());
    }


    @Test
    public void getProductsSuccessTest() {
        ArrayList<IProductModel> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetails();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsFailureTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordSuccessTest() {
        ArrayList<IProductModel> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("book");
        Assertions.assertEquals(1, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordFailureTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordSuccessTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordFailureTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordSuccessTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplayWithNullKeyword();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordFailureTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordSuccessTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("");
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordFailureTest() {
        ArrayList<IProductModel> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductByIdSuccessTest() {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getProductById(2);
        Assertions.assertNotNull(gotProduct);
    }

    @Test
    public void getProductByIdFailureTest() {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getProductById(1);
        Assertions.assertEquals(null, gotProduct);
    }

    @Test
    public void getProductByIdProductNotPresentInDBFailureTest() {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getNullProduct(1);
        Assertions.assertEquals(null, gotProduct);
    }

    @Test
    public void saveProductSuccessTest() {
        IProductModel productModel = new ProductModel("Pen", 3, "Blue, Black and red pen", 2, 120, true, "pen.jpg");
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.saveProduct(productModel);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_SUCCESS, result);
    }

    @Test
    public void saveProductFailureTest() {
        IProductModel productModel = new ProductModel();
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.NotSaveProduct(productModel);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_FAILURE, result);
    }

    @Test
    public void updateProductSuccessTest() {
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.updateProduct(1, 5, false);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_SUCCESS, result);
    }

    @Test
    public void updateProductFailureTest() {
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.NoUpdateProduct(1, 10, false);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_FAILURE, result);
    }

    @Test
    public void getLastProductIdSuccessTest() {
        Integer lastId;
        ProductDBMock product = new ProductDBMock();
        lastId = product.getLastProductId();
        Assertions.assertEquals(1, lastId);
    }

    @Test
    public void getLastProductIdFailureTest() {
        Integer lastId;
        ProductDBMock product = new ProductDBMock();
        lastId = product.getNullProductId();
        Assertions.assertEquals(null, lastId);
    }

    @Test
    public void getProductQuantitySuccessTest() {
        Integer productQuantity;
        ProductDBMock product = new ProductDBMock();
        productQuantity = product.getProductQuantity(1);
        Assertions.assertEquals(25, productQuantity);
    }

    @Test
    public void getProductQuantityFailureTest() {
        Integer productQuantity;
        ProductDBMock product = new ProductDBMock();
        productQuantity = product.getProductQuantity(5);
        Assertions.assertEquals(null, productQuantity);
    }

    @Test
    public void getTotalOfProductsSuccessTest() {
        HashMap<Integer, Integer> products = new HashMap<>();
        Integer total;
        products.put(1, 10);
        products.put(2, 20);
        ProductDBMock product = new ProductDBMock();
        total = product.getTotalOfProducts(products);
        Assertions.assertEquals(30, total);
    }

    @Test
    public void getTotalOfProductsFailureTest() {
        HashMap<Integer, Integer> products = new HashMap<>();
        Integer total;
        products.put(1, 10);
        products.put(2, 20);
        ProductDBMock product = new ProductDBMock();
        total = product.getWrongTotalOfProducts(products);
        Assertions.assertEquals(25, total);
    }

    @Test
    public void deleteProductSuccessTest() {
        Boolean result;
        ProductDBMock product = new ProductDBMock();
        result = product.deleteProduct(1);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void deleteProductFailureTest() {
        Boolean result;
        ProductDBMock product = new ProductDBMock();
        result = product.deleteProduct(2);
        Assertions.assertEquals(false, result);
    }
}
