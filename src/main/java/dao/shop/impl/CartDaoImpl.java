package dao.shop.impl;

import dao.shop.CartDao;
import model.shop.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public void addCart(Cart cart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Cart> findAllCarts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("[DEBUG] Hibernate Session 是否開啟：" + session.isOpen());
            System.out.println("[DEBUG] Hibernate Session 是否連線：" + session.isConnected());

            List<Cart> carts = session.createQuery("FROM Cart", Cart.class).list();
            System.out.println("[DEBUG] findAllCarts() 查詢到 " + (carts != null ? carts.size() : "NULL") + " 筆購物車");
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCart(Cart cart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(cart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(int cartId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cart cart = session.get(Cart.class, cartId);
            if (cart != null) {
                session.delete(cart);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}