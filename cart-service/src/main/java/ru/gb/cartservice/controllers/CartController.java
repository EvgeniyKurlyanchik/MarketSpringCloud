package ru.gb.cartservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.cartservice.convertors.CartConverter;
import ru.gb.cartservice.model.Cart;
import ru.gb.cartservice.services.CartService;


@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class CartController {
   private final CartService cartService;
   private final CartConverter cartConverter;
    @PostMapping
    public Cart getCurrentCart(@RequestBody String cartName) {
        return cartService.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id, @RequestBody String cartName) {
        cartService.addProductByIdToCart(id, cartName);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestBody String cartName) {
        cartService.getCurrentCart(cartName).clear();
    }

}


//    @GetMapping("/add/{id}")
//public Cart addToCart(@PathVariable Long id, String cartName) {
//        cartService.addProductByIdToCart(id,cartName);
//    return getCurrentCart(id,cartName);
//    }
//    @GetMapping("/clear")
//    public  void clearCart() {
//        cartService.clear();
//    }
//    @GetMapping("/remove/{id}")
//    public  void removeFromCart(@PathVariable Long id) {
//        cartService.remove(id);
//    }

//    @GetMapping
//    public Cart getCurrentCart(Long id,String cartName) {
//        return cartService.getCurrentCart(id,cartName);
//    }
//@GetMapping("/plus/{id}")
//    public Cart plusCart(@PathVariable Long id) {
//        cartService.add(id);
//        return getCurrentCart(id);
//    }


