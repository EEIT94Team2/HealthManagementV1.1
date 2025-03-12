package service.shop;

import model.shop.Cart;
import java.util.List;

public interface CartService {
    void addCart(Cart cart);
    List<Cart> findAllCarts();
    void updateCart(Cart cart);
    void deleteCart(int cartId);
}
