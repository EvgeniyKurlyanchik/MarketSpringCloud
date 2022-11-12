package ru.gb.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.coreservice.services.OrderService;




@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
       orderService.createOrder(username);
    }

//    @GetMapping
//    public List<OrderDto> getUserOrders(
//            @Parameter(description = "Имя текущего пользователя", required = true)
//            @RequestHeader String username
//    ) {
//        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
//    }

}
