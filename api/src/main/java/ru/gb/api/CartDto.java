package ru.gb.api;

import java.util.List;

public class CartDto {
    private List<OrderItemDto> items;

    private int totalPrice;

    public <R> CartDto(R collect, int totalPrice) {
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
