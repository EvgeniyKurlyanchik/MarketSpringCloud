package ru.gb.cartservice.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.api.ProductDto;



@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
//    private  final RestTemplate restTemplate;
private  final WebClient productServiceWebClient;

    public ProductDto findById(Long id) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }


}
