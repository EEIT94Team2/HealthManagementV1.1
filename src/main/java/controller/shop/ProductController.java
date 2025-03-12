package controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.shop.Product;
import service.shop.ProductService;
import service.shop.impl.ProductServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/shop/product/*")
public class ProductController extends ShopBaseController {

    private void sendJsonResponse(HttpServletResponse res, Map<String, Object> responseData) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        out.write(objectMapper.writeValueAsString(responseData));
        out.flush();
    }

    private ProductService productService = new ProductServiceImpl();

    // 新增產品
    public void add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            int stockQuantity = Integer.parseInt(req.getParameter("stockQuantity"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String imageUrl = req.getParameter("imageUrl");
            Timestamp createdAt = Timestamp.valueOf(req.getParameter("createdAt"));
            Timestamp updatedAt = Timestamp.valueOf(req.getParameter("updatedAt"));

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
            product.setCategoryId(categoryId);
            product.setImageUrl(imageUrl);
            product.setCreatedAt(createdAt.toLocalDateTime());
            product.setUpdatedAt(updatedAt.toLocalDateTime());

            productService.addProduct(product);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品新增成功");
            sendJsonResponse(res, response);
        } catch(Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品新增發生錯誤");
            sendJsonResponse(res, response);
        }
    }

    // 查詢產品
    public void find(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Product> products = productService.findAllProducts();
        if (products == null) {
            products = new ArrayList<>();  // 確保不是 null
        }

        System.out.println("[DEBUG] 查詢到 " + products.size() + " 筆產品");

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        PrintWriter out = res.getWriter();
        out.write(objectMapper.writeValueAsString(response));
        out.flush();
    }


    // 修改產品
    public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            int stockQuantity = Integer.parseInt(req.getParameter("stockQuantity"));
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String imageUrl = req.getParameter("imageUrl");
            Timestamp createdAt = Timestamp.valueOf(req.getParameter("createdAt"));
            Timestamp updatedAt = Timestamp.valueOf(req.getParameter("updatedAt"));

            Product product = new Product();
            product.setProductId(productId);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
            product.setCategoryId(categoryId);
            product.setImageUrl(imageUrl);
            product.setCreatedAt(createdAt.toLocalDateTime());
            product.setUpdatedAt(updatedAt.toLocalDateTime());

            productService.updateProduct(product);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品更新成功");
            sendJsonResponse(res, response);
        } catch(Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品更新發生錯誤");
            sendJsonResponse(res, response);
        }
    }

    // 刪除產品
    public void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            productService.deleteProduct(productId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品刪除成功");
            sendJsonResponse(res, response);
        } catch(Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "產品刪除發生錯誤");
            sendJsonResponse(res, response);
        }
    }
}
