package ru.gb.cartservice.convertors;

import org.springframework.stereotype.Component;
import ru.gb.api.CartItemDto;
import ru.gb.cartservice.utils.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem c) {
        return new CartItemDto(c.getProductId(), c.getProductTitle(), c.getQuantity(), c.getPricePerProduct(), c.getPrice());
    }
}
