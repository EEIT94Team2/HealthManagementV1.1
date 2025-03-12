package service.shop.impl;

import dao.shop.CartDao;
import dao.shop.impl.CartDaoImpl;
import model.shop.Cart;
import service.shop.CartService;
import java.util.List;

public class CartServiceImpl implements CartService {

    private CartDao cartDao = new CartDaoImpl();

    @Override
    public void addCart(Cart cart) {
        cartDao.addCart(cart);
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartDao.findAllCarts();
    }

    @Override
    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    @Override
    public void deleteCart(int cartId) {
        cartDao.deleteCart(cartId);
    }
}
