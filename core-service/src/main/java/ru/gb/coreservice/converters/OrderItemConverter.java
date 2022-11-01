package ru.gb.coreservice.converters;
import org.springframework.stereotype.Component;
import ru.gb.api.OrderItemDto;
import ru.gb.coreservice.entities.OrderItem;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
