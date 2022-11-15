package ru.gb.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.api.CartDto;
import ru.gb.coreservice.entities.Order;
import ru.gb.coreservice.entities.OrderItem;
import ru.gb.coreservice.integrations.CartServiceIntegration;
import ru.gb.coreservice.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
private final ProductService productService;
private final OrderRepository  orderRepository;

private final CartServiceIntegration cartServiceIntegration;
@Transactional
    public Order createOrder(String username) {
        CartDto cartDto= cartServiceIntegration.getCurrentCart();

        Order order =new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItemDto -> new OrderItem(
                        productService.findById(cartItemDto.getProductId()).get(),
                        order,
                        cartItemDto.getQuantity(),
                        cartItemDto.getPricePerProduct(),
                        cartItemDto.getPrice()
                )).collect(Collectors.toList()));
        orderRepository.save(order);
    return order;
    }

//    public List<Order> findOrdersByUsername(String username) {
//        return orderRepository.findAllByUsername(username);
//    }

}
