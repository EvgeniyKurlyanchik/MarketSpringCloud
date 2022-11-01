package ru.gb.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.OrderDetailsDto;
import ru.gb.api.OrderDto;
import ru.gb.api.ResourceNotFoundException;
import ru.gb.coreservice.converters.OrderConverter;
import ru.gb.coreservice.entities.User;
import ru.gb.coreservice.services.OrderService;
import ru.gb.coreservice.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @PostMapping("/{cartName}")
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user, orderDetailsDto, cartName);
    }

    @GetMapping
    public List<OrderDto> getCurrenUrders(Principal principal) {
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }


}
