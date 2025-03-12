package test;

import service.shop.OrderService;
import service.shop.impl.OrderServiceImpl;
import model.shop.Order;
import java.util.List;

public class OrderServiceTest {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();

        // 查詢所有訂單
        List<Order> orders = orderService.findAllOrders();
        System.out.println("從 Service 層查詢訂單：");
        orders.forEach(System.out::println);
    }
}