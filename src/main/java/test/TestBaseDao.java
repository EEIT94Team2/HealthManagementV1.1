package test;

import dao.shop.BaseDao;
import model.shop.Order;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


import java.util.List;

public class TestBaseDao {


    private static BaseDao baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDao();
    }


    @Test
    public void testBaseQueryObject() {
        // 查詢用戶數量 baseQueryObject  查詢結果集是單行 單列的 一個值的數據的方法
        String sql = "SELECT CAST(COUNT(1) AS BIGINT) FROM orders";
        Object result = baseDao.baseQueryObject(Long.class, sql);
        Long count = ((Number) result).longValue();
        System.out.println(count);

    }

    @Test
    public void testBaseQuery() {

        String sql = "SELECT order_id as orderId, user_id as userId, total_amount as totalAmount, status_id as statusId, created_at as createdAt FROM orders";
        List<Order> orderList = baseDao.baseQuery(Order.class, sql);
        orderList.forEach(System.out::println);


    }
    @Test
    public void testBaseUpdate() {
        String sql = "INSERT INTO orders (user_id, total_amount, status_id, created_at) VALUES (?, ?, ?, ?)";
        int rows = baseDao.baseUpdate(sql, 3, "10000", 0, "2021-09-01 00:00:00");
        System.out.println(rows);
    }
}