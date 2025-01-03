package com.example.nexis.controller;

import com.example.nexis.dto.UpdateCartDto;
import com.example.nexis.entity.Cart;
import com.example.nexis.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Get all cart items
    @GetMapping("/get")
    public ResponseEntity<List<Cart>> getCartItems(@RequestParam String userId) {
        List<Cart> cartItems = cartRepository.findByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

    // Update cart
    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody UpdateCartDto request) {
        Optional<Cart> cartOptional = cartRepository.findById(request.getProductId());
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            if ("add".equalsIgnoreCase(request.getActionType())) {
                cart.setQuantity(cart.getQuantity() + request.getQuantityChange());
            } else if ("remove".equalsIgnoreCase(request.getActionType())) {
                cart.setQuantity(Math.max(0, cart.getQuantity() - request.getQuantityChange()));
            }

            cartRepository.save(cart);
            return ResponseEntity.ok("Cart updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Reset cart (delete all items for the user)
    @DeleteMapping("/reset")
    public ResponseEntity<?> resetCart(@RequestParam String userId) {
        cartRepository.deleteByUserId(userId);
        return ResponseEntity.ok("Cart reset successfully");
    }
}
