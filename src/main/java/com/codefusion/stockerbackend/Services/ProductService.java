package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.ProductDto;
import com.codefusion.stockerbackend.Models.Product;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Repositories.ProductRepository;
import com.codefusion.stockerbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public Mono<Product> createProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        product.setQuantity(productDto.getQuantity());

        User user = new User();
        user.setId(productDto.getUserId());
        product.setUser(user);

        return productRepo.save(product);
    }

    public Flux<Product> getProducts(){
        return productRepo.findAll()
                .flatMap(product -> userRepo.findById(product.getUser().getId())
                        .map(user -> {
                            product.setUser(user);
                            return product;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }

    public Flux<Product> getProductsNoDetail(){
        return productRepo.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<Product> getProductById(String id){
        return productRepo.findById(id)
                .flatMap(product -> userRepo.findById(product.getUser().getId())
                        .map(user -> {
                            product.setUser(user);
                            return product;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Product> getProductByIdNoDetail(String id) {
        return productRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Product> updateProduct(ProductDto productDto, String id){
        return productRepo.findById(id)
                .flatMap(product -> {
                    product.setName(productDto.getName());
                    product.setCost(productDto.getCost());
                    product.setQuantity(productDto.getQuantity());

                    User user = new User();
                    user.setId(productDto.getUserId());
                    product.setUser(user);
                    return productRepo.save(product);
                        });
    }

    public Mono<Void> deleteProduct(String id){
        return productRepo.deleteById(id);
    }

}
