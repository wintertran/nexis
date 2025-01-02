package com.example.nexis.controller;

import com.example.nexis.entity.Cart;
import com.example.nexis.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Lấy giỏ hàng của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCartByUser(@PathVariable String userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Cart cart) {
        cartRepository.save(cart);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable String id, @RequestBody int quantity) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return ResponseEntity.badRequest().body("Cart item not found");
        }

        cart.setQuantity(quantity);
        cartRepository.save(cart);

        return ResponseEntity.ok("Cart updated successfully");
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable String id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return ResponseEntity.badRequest().body("Cart item not found");
        }

        cartRepository.delete(cart);
        return ResponseEntity.ok("Product removed from cart successfully");
    }
}
