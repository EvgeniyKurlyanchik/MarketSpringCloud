package ru.gb.coreservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.api.ProductDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void fullRestTest() {
        List<ProductDto> products = restTemplate.getForObject("/api/v1/products", List.class);
        assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(4);
    }
}
