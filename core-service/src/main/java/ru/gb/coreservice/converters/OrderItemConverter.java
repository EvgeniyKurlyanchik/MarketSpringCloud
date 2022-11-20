package ru.gb.coreservice.converters;

import org.springframework.stereotype.Component;
import ru.gb.api.OrderItemDto;
import ru.gb.coreservice.entities.OrderItem;


@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
