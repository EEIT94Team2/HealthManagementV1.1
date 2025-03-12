package controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.shop.Order;
import service.shop.OrderService;
import service.shop.impl.OrderServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/shop/order/*")
public class OrderController extends ShopBaseController {

    private OrderService orderService = new OrderServiceImpl();

    private void sendJsonResponse(HttpServletResponse res, Map<String, Object> responseData) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        // 註冊 JavaTimeModule 以支援 LocalDateTime 序列化
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        PrintWriter out = res.getWriter();
        out.write(objectMapper.writeValueAsString(responseData));
        out.flush();
    }

    // 新增訂單
    public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            BigDecimal totalAmount = new BigDecimal(req.getParameter("totalAmount"));
            int statusId = Integer.parseInt(req.getParameter("statusId"));
            // 假設前端傳送的時間格式為 "yyyy-MM-dd HH:mm:ss"，轉換為 LocalDateTime
            LocalDateTime createdAt = LocalDateTime.parse(req.getParameter("createdAt").replace(" ", "T"));

            Order order = new Order();
            order.setUserId(userId);
            order.setTotalAmount(totalAmount);
            order.setStatusId(statusId);
            order.setCreatedAt(createdAt);

            orderService.addOrder(order);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單新增成功");
            sendJsonResponse(res, response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單新增發生錯誤");
            sendJsonResponse(res, response);
        }
    }

    // 查詢所有訂單
    public void find(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            List<Order> orders = orderService.findAllOrders();
            Map<String, Object> response = new HashMap<>();
            response.put("orders", orders);
            sendJsonResponse(res, response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "查詢訂單發生錯誤");
            sendJsonResponse(res, response);
        }
    }

    // 修改訂單
    public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            BigDecimal totalAmount = new BigDecimal(req.getParameter("totalAmount"));
            int statusId = Integer.parseInt(req.getParameter("statusId"));
            LocalDateTime createdAt = LocalDateTime.parse(req.getParameter("createdAt").replace(" ", "T"));

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setTotalAmount(totalAmount);
            order.setStatusId(statusId);
            order.setCreatedAt(createdAt);

            orderService.updateOrder(order);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單更新成功");
            sendJsonResponse(res, response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單更新發生錯誤");
            sendJsonResponse(res, response);
        }
    }

    // 刪除訂單
    public void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            orderService.deleteOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單刪除成功");
            sendJsonResponse(res, response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "訂單刪除發生錯誤");
            sendJsonResponse(res, response);
        }
    }
}
