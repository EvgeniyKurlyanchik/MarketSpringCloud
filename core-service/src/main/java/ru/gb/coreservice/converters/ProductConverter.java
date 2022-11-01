package ru.gb.coreservice.converters;

import org.springframework.stereotype.Component;
import ru.gb.api.ProductDto;
import ru.gb.coreservice.entities.Product;


@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
