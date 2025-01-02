package com.example.nexis.service;

import com.example.nexis.entity.Cart;
import com.example.nexis.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addProductToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(String id) {
        cartRepository.deleteById(id);
    }

    public List<Cart> getCartByStatus(Cart status) {
        return cartRepository.findByStatus(status);
    }
}
