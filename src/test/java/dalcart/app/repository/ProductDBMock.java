package dalcart.app.repository;

import dalcart.app.models.Factories.IProductModelFactory;
import dalcart.app.models.Factories.ProductModelFactory;
import dalcart.app.models.Repository.IProductPersistence;
import dalcart.app.models.IProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDBMock implements IProductPersistence {

    @Override
    public ArrayList<IProductModel> getProductDetails() {
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        product_detail.add(product);
        product_detail.add(product);
        return product_detail;
    }

    public ArrayList<IProductModel> getNullList() {
        return null;
    }

    public ArrayList<IProductModel> getProductDetailsForDisplay() {
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        if (product.getEnabled()) {
            product_detail.add(product);
            product_detail.add(product);
        }
        return product_detail;
    }

    @Override
    public ArrayList<IProductModel> getProductDetailsForDisplay(String keyword) {
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        String productName = product.getProductName().toLowerCase();
        if (keyword == "" && product.getEnabled()) {
            product_detail.add(product);
            product_detail.add(product);
        } else if (productName.contains(keyword) && product.getEnabled()) {
            product_detail.add(product);
        }

        return product_detail;
    }

    public ArrayList<IProductModel> getProductDetailsForDisplayWithNullKeyword() {
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        product_detail.add(product);
        product_detail.add(product);
        return product_detail;
    }

    @Override
    public IProductModel getProductById(Integer productId) {
        IProductModelFactory productModelFactory = new ProductModelFactory();
        if (productId == 2) {
            IProductModel productModel = productModelFactory.createProductModel("Tshirts", 2, "Pure cotton Tshirts for summer", 12, 30, true, "tshirt.jpg");
            return productModel;
        }
        return null;
    }
    public IProductModel getNullProduct(Integer productId) {
        return null;
    }
    @Override
    public Integer getLastProductId() {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        Integer lastId = product.getProductId();
        return lastId;
    }

    public Integer getNullProductId() {
        return null;
    }

    @Override
    public StorageResult saveProduct(IProductModel product) {
        ArrayList<IProductModel> products = new ArrayList<>();
        Integer previousLength = products.size();
        products.add(product);
        Integer updatedLength = products.size();
        if (updatedLength == (previousLength + 1)) {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    public StorageResult NotSaveProduct(IProductModel product) {
        ArrayList<IProductModel> products = new ArrayList<>();
        Integer previousLength = products.size();
        Integer updatedLength = products.size();
        if (updatedLength == (previousLength + 1)) {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    @Override
    public StorageResult updateProduct(Integer productId, Integer productQuantity, Boolean productState) {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        product.setProductQuantity(productQuantity);
        product.setEnabled(productState);
        if (product.getProductQuantity() == productQuantity && product.getEnabled() == productState) {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    public StorageResult NoUpdateProduct(Integer productId, Integer productQuantity, Boolean productState) {
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");

        if (product.getProductQuantity() == productQuantity && product.getEnabled() == productState) {
            return StorageResult.STORAGE_SUCCESS;
        }
        return StorageResult.STORAGE_FAILURE;
    }

    @Override
    public Integer getProductQuantity(Integer productId) {
        Integer productQuantity;
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        if (productId == product.getProductId()) {
            productQuantity = product.getProductQuantity();
            return productQuantity;
        }
        return null;
    }

    @Override
    public Integer getTotalOfProducts(HashMap<Integer, Integer> products) {
        Integer total = 0;
        for (Map.Entry<Integer, Integer> p : products.entrySet()) {
            total += p.getValue();
        }
        return total;
    }

    public Integer getWrongTotalOfProducts(HashMap<Integer, Integer> products) {
        Integer total = 0;
        for (Map.Entry<Integer, Integer> p : products.entrySet()) {
            total = 25;
        }
        return total;
    }

    public Boolean deleteProduct(Integer productId) {
        ArrayList<IProductModel> product_detail = new ArrayList<>();
        IProductModelFactory factory = new ProductModelFactory();
        IProductModel product = factory.createProductModel("Book", 1, "A4 sized books are available", 5, 25, true, "book.jpg");
        product_detail.add(product);
        if (product.getProductId() == productId) {
            product_detail.remove(product);
        }
        if (product_detail.size() == 0) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean decreaseProductQuantity(HashMap<Integer, Integer> products) {
        return null;
    }
}
