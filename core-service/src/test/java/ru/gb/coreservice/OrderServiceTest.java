package ru.gb.coreservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.api.CartDto;
import ru.gb.api.CartItemDto;
import ru.gb.coreservice.entities.Order;
import ru.gb.coreservice.entities.Product;
import ru.gb.coreservice.integrations.CartServiceIntegration;
import ru.gb.coreservice.repositories.OrderRepository;
import ru.gb.coreservice.services.OrderService;
import ru.gb.coreservice.services.ProductService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
   @Autowired
    private OrderService orderService;
   @MockBean
    private  ProductService productService;
@MockBean
    private  OrderRepository orderRepository;
@MockBean
    private  CartServiceIntegration cartServiceIntegration;


   @Test
   public void createOrderTest(){
       CartDto cartDto =new CartDto();
       List<CartItemDto> items = new ArrayList<>();
         CartItemDto cartItemDto =new CartItemDto();
         cartItemDto.setProductTitle("Торт");
       cartItemDto.setPrice(BigDecimal.valueOf(1500));
       cartItemDto.setPricePerProduct(BigDecimal.valueOf(1500));
       cartItemDto.setProductId(1234L);
         cartItemDto.setQuantity(2);
        cartDto.setTotalPrice(BigDecimal.valueOf(3000));
    cartDto.setItems(List.of(cartItemDto));

       Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();
       Product product =new Product();
       product.setId(1234L);
       product.setTitle("Торт");
       product.setPrice(BigDecimal.valueOf(1500));

       Mockito.doReturn(Optional.of(product)).when(productService).findById(1234L);

       Order order =orderService.createOrder("admin") ;
       Assertions.assertEquals(order.getTotalPrice(),BigDecimal.valueOf(3000));

Mockito.verify(orderRepository,Mockito.times(1)).save(ArgumentMatchers.any());
   }

}
