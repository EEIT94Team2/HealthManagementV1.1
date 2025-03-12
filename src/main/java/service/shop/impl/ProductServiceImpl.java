package service.shop.impl;

import dao.shop.ProductDao;
import dao.shop.impl.ProductDaoImpl;
import model.shop.Product;
import service.shop.ProductService;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> products = productDao.findAllProducts();
        System.out.println("[DEBUG] ProductServiceImpl.findAllProducts() 查詢到 " + (products != null ? products.size() : "NULL") + " 筆產品");
        return products;
    }


    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }
}
