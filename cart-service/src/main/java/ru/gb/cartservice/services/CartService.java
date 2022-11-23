package ru.gb.cartservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gb.api.ProductDto;
import ru.gb.cartservice.integrations.ProductServiceIntegration;
import ru.gb.cartservice.utils.Cart;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            Cart cart = new Cart();
            carts.put(cartId, cart);
        }
        return carts.get(cartId);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        getCurrentCart(cartId).add(p);
    }

    public void clearCart(String cartId) {
        getCurrentCart(cartId).clear();
    }
//    public void removeFromCart(String cartId, Long productId) {
//        execute(cartId, cart -> cart.remove(productId));
//    }
}
