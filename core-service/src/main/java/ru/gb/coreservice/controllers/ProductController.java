package ru.gb.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.ProductDto;
import ru.gb.coreservice.converters.ProductConverter;
import ru.gb.coreservice.entities.Product;
import ru.gb.api.ResourceNotFoundException;
import ru.gb.coreservice.services.ProductService;
import ru.gb.coreservice.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }


//    @GetMapping("/{id}")
//    public ProductDto findProductById(@PathVariable Long id){
//        Product product=productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("продукт не найден ,id="+ id));
//        return new ProductDto(product.getId(),product.getTitle(),product.getPrice()) ;
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createNewProducts(@RequestBody CreateNewProductDto createNewProductDto) {
//        productService.createNewProduct(createNewProductDto);
//    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
