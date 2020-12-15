package shop.example.shop.service;

import shop.example.shop.domain.Cart;
import shop.example.shop.domain.User;
import shop.example.shop.dto.CartDto;

import java.util.List;


public interface CartService {
    Cart createCart(User user, List<Long> productIds);
    void addProducts(Cart cart, List<Long> productIds);
    CartDto getCartByUser(String name);
}
