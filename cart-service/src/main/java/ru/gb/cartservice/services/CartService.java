package ru.gb.cartservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.api.ProductDto;
import ru.gb.cartservice.integrations.ProductServiceIntegration;
import ru.gb.cartservice.utils.Cart;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }
}
