package util;

import model.shop.Cart;
import model.shop.Order;
import model.shop.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 1️⃣ 建立 Hibernate 設定
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // 讀取 hibernate.cfg.xml

            // 2️⃣ 註冊所有 `@Entity` 類別（符合 Hibernate 5）
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Cart.class);
            configuration.addAnnotatedClass(Order.class);


            // 3️⃣ 建立 ServiceRegistry（符合 Hibernate 5）
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // 4️⃣ 創建 SessionFactory
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("[錯誤] 初始化 SessionFactory 失敗：" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        System.out.println("[DEBUG] HibernateUtil: 取得 SessionFactory");
        return sessionFactory;
    }

    public static void shutdown() {
        // 釋放 Hibernate 資源
        getSessionFactory().close();
    }
}
