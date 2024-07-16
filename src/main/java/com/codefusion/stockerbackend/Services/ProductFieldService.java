package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.ProductFieldDto;
import com.codefusion.stockerbackend.Models.Product;
import com.codefusion.stockerbackend.Models.ProductField;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Repositories.ProductFieldRepository;
import com.codefusion.stockerbackend.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductFieldService {

    private final ProductFieldRepository productFieldRepo;
    private final ProductRepository productRepo;

    public Mono<ProductField> createProductField(ProductFieldDto productFieldDto){
        ProductField productField = new ProductField();
        productField.setName(productFieldDto.getName());
        productField.setValue(productFieldDto.getValue());

        Product product = new Product();
        product.setId(productFieldDto.getProductId());
        productField.setProduct(product);

        return productFieldRepo.save(productField);
    }

    public Flux<ProductField> getProductFields(){
        return productFieldRepo.findAll()
                .flatMap(productField -> productRepo.findById(productField.getProduct().getId())
                        .map(product -> {
                            productField.setProduct(product);
                            return productField;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }

    public Flux<ProductField> getProductFieldsNoDetail(){
        return productFieldRepo
                .findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<ProductField> getProductFieldById(String id){
        return productFieldRepo.findById(id)
                .flatMap(productField -> productRepo.findById(productField.getProduct().getId())
                        .map(product -> {
                            productField.setProduct(product);
                            return productField;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ProductField> getProductFieldByIdNoDetail(String id) {
        return productFieldRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ProductField> updateProductField(ProductFieldDto productFieldDto, String id){
        return productFieldRepo.findById(id)
                .flatMap(productField -> {
                    productField.setName(productFieldDto.getName());
                    productField.setValue(productFieldDto.getValue());

                    Product product = new Product();
                    product.setId(productFieldDto.getProductId());
                    productField.setProduct(product);

                    return productFieldRepo.save(productField);
                });
    }

    public Mono<Void> deleteProductField(String id){
        return productFieldRepo.deleteById(id);
    }
}
