package ru.gb.cartservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;



    private final RedisTemplate<String, Object> redisTemplate;
    public Cart getCurrentCart(String cartId) {
        if (!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }
        return (Cart)redisTemplate.opsForValue().get(cartId);
    }


    public void removeFromCart(String cartId, Long productId) {
        execute(cartId, cart -> cart.remove(productId));
    }
    private void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }
    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }
    public void addToCart(String cartId, Long productId) {
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            cart.add(p);
        });
    }
    public void remove(String cartId, Long productId) {
        execute(cartId, cart -> cart.remove(productId));
    }
}
//    private Map<String, Cart> carts;

//    @PostConstruct
//    public void init() {
//        carts = new HashMap<>();
//    }
//    public Cart getCurrentCart(String cartId) {
//        if (!carts.containsKey(cartId)) {
//            Cart cart = new Cart();
//            carts.put(cartId, cart);
//        }
//        return carts.get(cartId);
//    }

//    public void addToCart(String cartId, Long productId) {
//        ProductDto p = productServiceIntegration.findById(productId);
//        getCurrentCart(cartId).add(p);
//    }

//    public void clearCart(String cartId) {
//        getCurrentCart(cartId).clear();
//    }