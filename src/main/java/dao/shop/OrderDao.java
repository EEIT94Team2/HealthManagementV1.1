package dao.shop;

import model.shop.Order;

import java.util.List;

/**
 *
 */
public interface OrderDao {

    /**
     * 用於向數據庫中增加一份訂單紀錄
     * @param order 訂單數據以sysOrder實體類對象
     */
    void addOrder(Order order);


    /**
     * 用於查詢數據庫中所有的訂單紀錄
     * @return  返回一個List集合,集合中存儲所有的訂單紀錄
     */
    List<Order> findAllOrders();

    /**
     * 用於更新數據庫中的訂單紀錄
     * @param order 訂單數據以Order實體類對象
     */
    void updateOrder(Order order);

    /**
     * 用於刪除數據庫中的訂單紀錄
     * @param orderId 訂單編號
     */
    void deleteOrder(int orderId);
}
