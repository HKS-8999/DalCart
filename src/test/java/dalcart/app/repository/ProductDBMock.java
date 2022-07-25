package dalcart.app.repository;

import dalcart.app.Factories.IProductModelFactory;
import dalcart.app.Factories.ProductModelFactory;
import dalcart.app.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;
import dalcart.app.models.ProductModelMock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        if(keyword == "" && product.getEnabled())
        {
            product_detail.add(product);
            product_detail.add(product);
        }
        else if(productName.contains(keyword) && product.getEnabled())
        {
            product_detail.add(product);
        }

        return product_detail;
    }

    public ArrayList<ProductModelMock> getProductDetailsForDisplayWithNullKeyword()
    {
        ArrayList<ProductModelMock> product_detail = new ArrayList<>();
        ProductModelMock product = new ProductModelMock();
        product_detail.add(product);
        product_detail.add(product);
        return product_detail;
    }

    @Override
    public IProductModel getProductById(Integer productId)
    {
        IProductModelFactory productModelFactory = new ProductModelFactory();
        if(productId == 2)
        {
            IProductModel productModel = productModelFactory.createProductModel("Tshirts",2,"Pure cotton Tshirts for summer",12,30,true, "tshirt.jpg");
            return productModel;
        }
        return null;
    }

    public IProductModel getNullProduct(Integer productId)
    {
        return null;
    }

    @Override
    public Integer getLastProductId()
    {
        ProductModelMock product = new ProductModelMock();
        Integer lastId = product.getProductId();
        return lastId;
    }

    public Integer getNullProductId()
    {
        return null;
    }

    @Override
    public StorageResult saveProduct(IProductModel product)
    {
        ArrayList<IProductModel> products = new ArrayList<>();
        Integer previousLength = products.size();
        products.add(product);
        Integer updatedLength = products.size();
        if(updatedLength == (previousLength + 1))
        {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    public StorageResult NotSaveProduct(IProductModel product)
    {
        ArrayList<IProductModel> products = new ArrayList<>();
        Integer previousLength = products.size();
//        products.add(product);
        Integer updatedLength = products.size();
        if(updatedLength == (previousLength + 1))
        {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    @Override
    public StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
        ProductModelMock product = new ProductModelMock();
        product.setProductQuantity(productQuantity);
        product.setEnabled(productState);
        if(product.getProductQuantity() == productQuantity && product.getEnabled() == productState)
        {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    public StorageResult NoUpdateProduct(Integer productId, Integer productQuantity, Boolean productState)
    {
        ProductModelMock product = new ProductModelMock();
        if(product.getProductQuantity() == productQuantity && product.getEnabled() == productState)
        {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    @Override
    public Integer getProductQuantity(Integer productId)
    {
        Integer productQuantity;
        ProductModelMock product = new ProductModelMock("Bottles", 2, "Steel bottles are available", 10, 35, true, "bottle.jpg");
        if(productId == product.getProductId())
        {
            productQuantity = product.getProductQuantity();
            return productQuantity;
        }
        return null;
    }

    @Override
    public Integer getTotalOfProducts(HashMap<Integer, Integer> products)
    {
        Integer total = 0;
        for(Map.Entry<Integer, Integer> p : products.entrySet())
        {
            total += p.getValue();
        }
        return total;
    }

    public Integer getWrongTotalOfProducts(HashMap<Integer, Integer> products)
    {
        Integer total = 0;
        for(Map.Entry<Integer, Integer> p : products.entrySet())
        {
            total = 25;
        }
        return total;
    }
}
