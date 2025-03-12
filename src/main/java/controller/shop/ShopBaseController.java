package controller.shop;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class ShopBaseController extends HttpServlet {
    public ShopBaseController() {
        System.out.println("✅ ShopBaseController initial!");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("✅ ShopBaseController getRequest: " + req.getRequestURI()); // 測試用
        // 判斷此次請求時是增?刪?改?查?
        String requestURI = req.getRequestURI(); // /order/add
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];

        //  檢查是否為 JSP 頁面
        if (isJspPage(methodName)) {
            String jspPath = "/WEB-INF/jsp/shop/" + methodName + ".jsp";
            System.out.println("✅ 轉發 JSP: " + jspPath);
            req.getRequestDispatcher(jspPath).forward(req, resp);
            return;
        }

        //使用 反射 通過方法名獲取下面的方法
        Class aClass = this.getClass();
        //通過方法名獲取方法對象
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //暴力破解方法的訪問修飾服的限制
            declaredMethod.setAccessible(true);
            //調用方法
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 上方改寫
        if (methodName.equals("add")) {
            //做增加處理
            add(req, resp);
        }else if(methodName.equals("find")){
            //做查詢處理
            find(req, resp);
        }else if(methodName.equals("update")){
            //做修改處理
            update(req, resp);
        }else if (methodName.equals("remove")){
            //做刪除處理
            remove(req, resp);
        }
        */


    }
    // 🔍 判斷請求是否為 JSP 頁面
    private boolean isJspPage(String pageName) {
        return "cart".equals(pageName) || "order".equals(pageName) || "product".equals(pageName);
    }
}
