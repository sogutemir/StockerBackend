package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.ProductDto;
import com.codefusion.stockerbackend.Models.Product;
import com.codefusion.stockerbackend.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Mono<Product> createProducts(@RequestBody ProductDto productDto){
        return service.createProduct(productDto);
    }

    @GetMapping
    public Flux<Product> getAllProductsND(){
        return service.getProductsNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<Product> getAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductND(@PathVariable String id){
        return service.getProductByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<Product> getProduct(@PathVariable String id){
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable String id){
        return service.updateProduct(productDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteProduct(id);
    }
}
