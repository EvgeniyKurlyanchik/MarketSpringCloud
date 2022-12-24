package ru.gb.coreservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.api.CartDto;
import ru.gb.coreservice.entities.Order;
import ru.gb.coreservice.entities.OrderItem;
import ru.gb.coreservice.exceptions.ResourceNotFoundException;
import ru.gb.coreservice.integrations.CartServiceIntegration;
import ru.gb.coreservice.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
private final ProductService productService;
private final OrderRepository  orderRepository;

private final CartServiceIntegration cartServiceIntegration;
    @Transactional
    public void createNewOrder(String username) {
        CartDto cart = cartServiceIntegration.getCurrentUserCart(username);
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Нельзя оформить заказ для пустой корзины");
        }
        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setUsername(username);
        order.setItems(new ArrayList<>());
        cart.getItems().forEach(ci -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            oi.setPricePerProduct(ci.getPricePerProduct());
            oi.setProduct(productService.findById(ci.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
            order.getItems().add(oi);
        });
        orderRepository.save(order);
        cartServiceIntegration.clearCart(username);

    }





//@Transactional
//    public Order createOrder(String username) {
//        CartDto cartDto= cartServiceIntegration.getCurrentCart();
//
//        Order order =new Order();
//        order.setUsername(username);
//        order.setTotalPrice(cartDto.getTotalPrice());
//        order.setItems(cartDto.getItems().stream().map(
//                cartItemDto -> new OrderItem(
//                        productService.findById(cartItemDto.getProductId()).get(),
//                        order,
//                        cartItemDto.getQuantity(),
//                        cartItemDto.getPricePerProduct(),
//                        cartItemDto.getPrice()
//                )).collect(Collectors.toList()));
//        orderRepository.save(order);
//    return order;
//    }

    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }

}
