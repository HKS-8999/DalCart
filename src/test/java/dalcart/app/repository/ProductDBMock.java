package dalcart.app.repository;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModelMock;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDBMock implements IProductPersistence
{

    @Override
    public ArrayList<ProductModelMock> getProductDetails()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        ProductModelMock product = new ProductModelMock();
        product_detail.add(product);
        product_detail.add(product);
        return product_detail;
    }

    public ArrayList<ProductModelMock> getNullList()
    {
        return null;
    }

    public ArrayList<ProductModelMock> getProductDetailsForDisplay()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        ProductModelMock product = new ProductModelMock();
        if(product.getEnabled())
        {
            product_detail.add(product);
            product_detail.add(product);
        }
        return product_detail;
    }
    @Override
    public ArrayList<ProductModelMock> getProductDetailsForDisplay(String keyword)
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
//        IProductModelFactory productModelFactory = new ProductModelFactory();
//        if()
//        IProductModel productModel = productModelFactory.createProductModel("Tshirts",2,"Pure cotton Tshirts for summer",12,30,true, "tshirt.jpg");
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
