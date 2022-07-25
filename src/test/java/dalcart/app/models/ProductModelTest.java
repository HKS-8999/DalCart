package dalcart.app.models;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.repository.ProductDBMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

public class ProductModelTest
{
    @Test
    public void getProductsSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetails();
        Assertions.assertEquals(2,product_detail.size());
    }

    @Test
    public void getProductsFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null,product_detail);
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
        Assertions.assertEquals(null,product_detail);
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay();
        Assertions.assertEquals(2,product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null,product_detail);
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay(null);
        Assertions.assertEquals(2,product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null,product_detail);
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("");
        Assertions.assertEquals(2,product_detail.size());
    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail;
        ProductDBMock product = new ProductDBMock();
        product_detail = product.getNullList();
        Assertions.assertEquals(null,product_detail);
    }

    @Test
    public void getProductByIdSuccessTest()
    {

    }

    @Test
    public void getProductByIdFailureTest()
    {

    }

    @Test
    public void saveProductSuccessTest()
    {

    }

    @Test
    public void saveProductFailureTest()
    {

    }

    @Test
    public void updateProductSuccessTest()
    {

    }

    @Test
    public void updateProductFailureTest()
    {

    }

    @Test
    public void getLastProductIdSuccessTest()
    {

    }

    @Test
    public void getLastProductIdFailureTest()
    {

    }

    @Test
    public void getProductQuantitySuccessTest()
    {

    }

    @Test
    public void getProductQuantityFailureTest()
    {

    }

    @Test
    public void getTotalOfProductsSuccessTest()
    {

    }

    @Test
    public void getTotalOfProductsFailureTest()
    {

    }
}
