package dalcart.app.repository;

import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModelMock;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDBMock implements IProductPersistence
{

    @Override
    public ArrayList getProductDetails()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        ProductModelMock product = new ProductModelMock();
        product_detail.add(product);
        product_detail.add(product);
        return product_detail;
    }

    @Override
    public ArrayList getProductDetailsForDisplay(String keyword)
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        ProductModelMock product = new ProductModelMock();
        String productName = product.getProductName().toLowerCase();
        if(productName.contains(keyword) && product.getEnabled())
        {
            product_detail.add(product);
        }
        if((keyword == null || keyword == "") && product.getEnabled())
        {
            product_detail.add(product);
            product_detail.add(product);
        }
        return product_detail;
    }

    @Override
    public IProductModel getProductById(Integer productId)
    {
        return null;
    }

    @Override
    public Integer getLastProductId()
    {
        return null;
    }

    @Override
    public StorageResult saveProduct(IProductModel product)
    {
        return null;
    }

    @Override
    public StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
        return null;
    }

    @Override
    public Integer getProductQuantity(Integer productId)
    {
        return null;
    }

    @Override
    public Integer getTotalOfProducts(HashMap<Integer, Integer> products)
    {
        return null;
    }
}
