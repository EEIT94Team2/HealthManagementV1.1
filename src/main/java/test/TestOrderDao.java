package test;

import dao.shop.OrderDao;
import dao.shop.impl.OrderDaoImpl;
import model.shop.Order;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestOrderDao {


    private static OrderDao orderDao;

    @BeforeClass
    public static void initOrderDao() {
        orderDao = new OrderDaoImpl();
    }

    @Test
    public void testFindAllOrders() {
        List<Order> orders = orderDao.findAllOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void testUpdateOrder() {
        // Implement the update method in SysOrderDao and SysOrderDaoImpl first
        // Example:
        // int rows = orderDao.updateOrder(new SysOrder(1, 2, new BigDecimal("2000.00"), 2, LocalDateTime.now()));
        // System.out.println("Rows affected: " + rows);
    }

    @Test
    public void testDeleteOrder() {
        // Implement the delete method in SysOrderDao and SysOrderDaoImpl first
        // Example:
        // int rows = orderDao.deleteOrder(1);
        // System.out.println("Rows affected: " + rows);
    }


}
