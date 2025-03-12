package test;

import org.hibernate.SessionFactory;
import util.HibernateUtil;
import java.util.Set;

public class HibernateEntityTest {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("[測試] Hibernate 成功取得 SessionFactory！");

            // 🔥 取得 Hibernate 內部已加載的實體類別
            Set<String> entityNames = sessionFactory.getMetamodel().getEntities().stream()
                    .map(entityType -> entityType.getName())
                    .collect(java.util.stream.Collectors.toSet());

            System.out.println("[測試] Hibernate 內部已加載的實體類別：");
            for (String entityName : entityNames) {
                System.out.println("  - " + entityName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}