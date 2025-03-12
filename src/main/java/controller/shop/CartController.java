package controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.shop.Cart;
import service.shop.CartService;
import service.shop.impl.CartServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/shop/cart/*")
public class CartController extends ShopBaseController {

    private CartService cartService = new CartServiceImpl();

    private void sendJsonResponse(HttpServletResponse res, Map<String, Object> responseData) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        // 註冊 JavaTimeModule 以支援 LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        PrintWriter out = res.getWriter();
        out.write(objectMapper.writeValueAsString(responseData));
        out.flush();
    }

    public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            Cart cart = new Cart(userId, productId, quantity);
            cartService.addCart(cart);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "購物車新增成功");
            sendJsonResponse(res, response);
        } catch (Exception e) {
            e.printStackTrace();
            sendJsonResponse(res, Map.of("message", "購物車新增失敗"));
        }
    }

    public void find(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[DEBUG] CartController.find() 被呼叫");

        List<Cart> carts = cartService.findAllCarts();

        if (carts == null) {
            System.out.println("[ERROR] cartService.findAllCarts() 回傳 null");
        } else {
            System.out.println("[DEBUG] 找到 " + carts.size() + " 筆購物車資料");
        }

        sendJsonResponse(res, Map.of("carts", carts));
    }
}
