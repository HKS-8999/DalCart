package dalcart.app.models;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.repository.ProductDBMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductModelTest
{
    @Test
    public void getProductsSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetails();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("book");
        Assertions.assertEquals(1, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplayWithNullKeyword();
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("");
        Assertions.assertEquals(2, product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null, product_detail);
    }

    @Test
    public void getProductByIdSuccessTest()
    {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getProductById(2);
        Assertions.assertNotNull(gotProduct);
    }

    @Test
    public void getProductByIdFailureTest()
    {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getProductById(1);
        Assertions.assertEquals(null, gotProduct);
    }

    @Test
    public void getProductByIdProductNotPresentInDBFailureTest()
    {
        IProductModel gotProduct;
        ProductDBMock product = new ProductDBMock();
        gotProduct = product.getNullProduct(1);
        Assertions.assertEquals(null, gotProduct);
    }

    @Test
    public void saveProductSuccessTest()
    {
        IProductModel productModel = new ProductModel("Pen", 3, "Blue, Black and red pen", 2, 120, true, "pen.jpg");
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.saveProduct(productModel);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_SUCCESS, result);
    }

    @Test
    public void saveProductFailureTest()
    {
        IProductModel productModel = new ProductModel();
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.NotSaveProduct(productModel);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_FAILURE, result);
    }

    @Test
    public void updateProductSuccessTest()
    {
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.updateProduct(1, 5, false);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_SUCCESS, result);
    }

    @Test
    public void updateProductFailureTest()
    {
        ProductDBMock product = new ProductDBMock();
        IProductPersistence.StorageResult result = product.NoUpdateProduct(1, 10, false);
        Assertions.assertEquals(IProductPersistence.StorageResult.STORAGE_FAILURE, result);
    }

    @Test
    public void getLastProductIdSuccessTest()
    {
        Integer lastId;
        ProductDBMock product = new ProductDBMock();
        lastId = product.getLastProductId();
        Assertions.assertEquals(1, lastId);
    }

    @Test
    public void getLastProductIdFailureTest()
    {
        Integer lastId;
        ProductDBMock product = new ProductDBMock();
        lastId = product.getNullProductId();
        Assertions.assertEquals(null, lastId);
    }

    @Test
    public void getProductQuantitySuccessTest()
    {
        Integer productQuantity;
        ProductDBMock product = new ProductDBMock();
        productQuantity = product.getProductQuantity(2);
        Assertions.assertEquals(35, productQuantity);
    }

    @Test
    public void getProductQuantityFailureTest()
    {
        Integer productQuantity;
        ProductDBMock product = new ProductDBMock();
        productQuantity = product.getProductQuantity(5);
        Assertions.assertEquals(null, productQuantity);
    }

    @Test
    public void getTotalOfProductsSuccessTest()
    {
        HashMap<Integer, Integer> products = new HashMap<>();
        Integer total;
        products.put(1, 10);
        products.put(2, 20);
        ProductDBMock product = new ProductDBMock();
        total = product.getTotalOfProducts(products);
        Assertions.assertEquals(30, total);
    }

    @Test
    public void getTotalOfProductsFailureTest()
    {
        HashMap<Integer, Integer> products = new HashMap<>();
        Integer total;
        products.put(1, 10);
        products.put(2, 20);
        ProductDBMock product = new ProductDBMock();
        total = product.getWrongTotalOfProducts(products);
        Assertions.assertEquals(25, total);
    }
}
