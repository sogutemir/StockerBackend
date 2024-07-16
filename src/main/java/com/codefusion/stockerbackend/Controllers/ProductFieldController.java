package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.ProductFieldDto;
import com.codefusion.stockerbackend.Models.ProductField;
import com.codefusion.stockerbackend.Services.ProductFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productfields")
public class ProductFieldController {

    private final ProductFieldService service;

    @PostMapping
    public Mono<ProductField> createProductFields(@RequestBody ProductFieldDto ProductFieldDto){
        return service.createProductField(ProductFieldDto);
    }

    @GetMapping
    public Flux<ProductField> getAllProductFieldsND(){
        return service.getProductFieldsNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<ProductField> getAllProductFields(){
        return service.getProductFields();
    }

    @GetMapping("/{id}")
    public Mono<ProductField> getProductFieldND(@PathVariable String id){
        return service.getProductFieldByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<ProductField> getProductField(@PathVariable String id){
        return service.getProductFieldById(id);
    }

    @PutMapping("/{id}")
    public Mono<ProductField> updateProductField(@RequestBody ProductFieldDto productFieldDto, @PathVariable String id){
        return service.updateProductField(productFieldDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProductField(@PathVariable String id){
        return service.deleteProductField(id);
    }
}
