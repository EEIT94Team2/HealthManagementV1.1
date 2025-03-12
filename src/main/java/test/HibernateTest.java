package test;

import model.shop.Cart;
import model.shop.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;


public class HibernateTest {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Cart> carts = session.createQuery("FROM Cart", Cart.class).list();
            System.out.println("[測試] 查詢到 " + carts.size() + " 筆購物車資料");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}