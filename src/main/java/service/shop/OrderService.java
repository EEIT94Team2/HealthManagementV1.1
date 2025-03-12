package service.shop;

import model.shop.Order;
import java.util.List;

/**
 * 該介面定義了orders表格為核心的業務處理功能
 */


public interface OrderService {
    void addOrder(Order order);
    List<Order> findAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}
