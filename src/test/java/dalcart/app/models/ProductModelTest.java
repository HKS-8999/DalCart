package dalcart.app.models;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.repository.ProductDBMock;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class ProductModelTest
{
    @Test
    public void getProductsSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail;
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetails();
        assertTrue(product_detail.size() > 0);
    }

    @Test
    public void getProductsFailureTest()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        IProductPersistence product = new ProductDBMock();
//        product_detail = product.getProductDetails();
        assertTrue(product_detail.get(0) == null);
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordSuccessTest()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        IProductPersistence product = new ProductDBMock();
        product_detail = product.getProductDetailsForDisplay("book");
        assertTrue(product_detail.size() > 0);
    }

    @Test
    public void getProductsToDisplayWithSearchKeywordFailureTest()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        IProductPersistence product = new ProductDBMock();
        assertTrue(product_detail.get(0) == null);
    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordSuccessTest()
    {

    }

    @Test
    public void getProductsToDisplayWithoutSearchKeywordFailureTest()
    {

    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordSuccessTest()
    {

    }

    @Test
    public void getProductsToDisplayWithNullSearchKeywordFailureTest()
    {

    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordSuccessTest()
    {

    }

    @Test
    public void getProductsToDisplayWithEmptySearchKeywordFailureTest()
    {

    }


//    @Test
//    public void getProductsToDisplayFailureTest()
//    {
//        ArrayList<ProductModelMock> product_detail;
//        IProductPersistence product = new ProductDBMock();
//        product_detail = product.getProductDetails();
//        product_detail.get(0).setEnabled(false);
//        boolean enabled = product_detail.get(0).getEnabled();
//        assertTrue(product_detail.size() == 0 && enabled == false);
//    }

    //To do: search key word test cases remaining

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
