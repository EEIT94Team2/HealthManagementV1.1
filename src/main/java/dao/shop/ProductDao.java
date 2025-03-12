package dao.shop;

import model.shop.Product;
import java.util.List;

public interface ProductDao {
    /**
     * 用於向數據庫中增加一份產品紀錄
     * @param product 產品數據以sysProduct實體類對象
     */
    void addProduct(Product product);

    /**
     * 用於查詢數據庫中所有的產品紀錄
     * @return 返回一個List集合,集合中存儲所有的產品紀錄
     */
    List<Product> findAllProducts();

    /**
     * 用於更新數據庫中的產品紀錄
     * @param product 產品數據以Product實體類對象
     */
    void updateProduct(Product product);

    /**
     * 用於刪除數據庫中的產品紀錄
     * @param productId 產品編號
     */
    void deleteProduct(int productId);

}
