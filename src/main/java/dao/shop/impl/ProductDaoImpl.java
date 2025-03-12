package dao.shop.impl;

import dao.shop.ProductDao;
import model.shop.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void addProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("[DEBUG] Hibernate Session 是否開啟：" + session.isOpen());
            System.out.println("[DEBUG] Hibernate Session 是否連線：" + session.isConnected());

            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            System.out.println("[DEBUG] findAllProducts() 查詢到 " + (products != null ? products.size() : "NULL") + " 筆產品");
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public void updateProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.delete(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
