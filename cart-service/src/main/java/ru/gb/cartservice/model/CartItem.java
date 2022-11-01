package ru.gb.cartservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.api.OrderItemDto;

@Data

@NoArgsConstructor
public class CartItem extends OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private  int price;

    public CartItem(Long productId, String productTitle, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public void changeQuantity(int delta) {
        quantity+= delta;
        price = pricePerProduct*quantity;
    }

}
