package ru.gb.coreservice.converters;

import org.springframework.stereotype.Component;
import ru.gb.api.ProductDto;
import ru.gb.coreservice.entities.Product;


@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {
        ProductDto productDto = new ProductDto();
        productDto.setId(p.getId());
        productDto.setTitle(p.getTitle());
        productDto.setPrice(p.getPrice());
        productDto.setCategoryTitle(p.getCategory().getTitle());
        return productDto;
    }
}
