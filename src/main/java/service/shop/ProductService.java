package service.shop;

import model.shop.Product;
import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    List<Product> findAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int productId);
}
