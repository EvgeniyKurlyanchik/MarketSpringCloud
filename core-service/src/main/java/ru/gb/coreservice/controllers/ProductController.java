package ru.gb.coreservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.ProductDto;
import ru.gb.coreservice.converters.ProductConverter;
import ru.gb.coreservice.entities.Product;
import ru.gb.coreservice.exceptions.AppError;
import ru.gb.coreservice.exceptions.ResourceNotFoundException;
import ru.gb.coreservice.repositories.specifications.ProductsSpecifications;
import ru.gb.coreservice.services.ProductService;

import java.math.BigDecimal;
//http://localhost:8189/market-core/swagger-ui/index.html#/
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Методы работы с продуктами")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1")@Parameter(description = "Номер страницы", required = true) Integer page,
            @RequestParam(name = "page_size", defaultValue = "5") @Parameter(description = "Номер страницы", required = false) Integer pageSize,
            @RequestParam(name = "title_part", required = false) @Parameter(description = "Фильтр части названия продукта", required = false) String titlePart,
            @RequestParam(name = "min_price", required = false) @Parameter(description = "Фильтр по мин цене продукта", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) @Parameter(description = "Фильтр по макс цене продукта", required = false)Integer maxPrice
    ) {
        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = Specification.where(null);
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(minPrice)));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(maxPrice)));
        }
        return productService.findAll(page - 1, pageSize, spec).map(productConverter::entityToDto);
    }
    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )



    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден")));
    }
    @Operation(
            summary = "Запрос на создание нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }
    @Operation(
            summary = "Удаление продукта по ID",
            responses = {
                    @ApiResponse(
                            description = "Продукт удален", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}