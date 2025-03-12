package dao.shop;

import model.shop.Cart;
import java.util.List;

public interface CartDao {
    /**
     * 用於向數據庫中增加一份購物車紀錄
     * @param cart 購物車數據以Cart實體類對象
     */
    void addCart(Cart cart);

    /**
     * 用於查詢數據庫中所有的購物車紀錄
     * @return 返回一個List集合,集合中存儲所有的購物車紀錄
     */
    List<Cart> findAllCarts();

    /**
     * 用於更新數據庫中的購物車紀錄
     * @param cart 購物車數據以Cart實體類對象
     */
    void updateCart(Cart cart);

    /**
     * 用於刪除數據庫中的購物車紀錄
     * @param cartId 購物車編號
     */
    void deleteCart(int cartId);
}
