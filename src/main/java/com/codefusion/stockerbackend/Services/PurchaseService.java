package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.PurchaseDto;
import com.codefusion.stockerbackend.Models.Product;
import com.codefusion.stockerbackend.Models.Purchase;
import com.codefusion.stockerbackend.Repositories.ProductRepository;
import com.codefusion.stockerbackend.Repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public Mono<Purchase> createPurchase(PurchaseDto purchaseDto){
        Purchase purchase = new Purchase();
        purchase.setPrice(purchaseDto.getPrice());
        purchase.setQuantity(purchaseDto.getQuantity());
        purchase.setEntryDateTime(purchaseDto.getEntryDateTime());

        Product product = new Product();
        product.setId(purchaseDto.getProductId());
        purchase.setProduct(product);

        return purchaseRepository.save(purchase);
    }

    public Flux<Purchase> getPurchases(){
        return purchaseRepository.findAll()
                .flatMap(purchase -> productRepository.findById(purchase.getProduct().getId())
                        .map(product -> {
                            purchase.setProduct(product);
                            return purchase;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }

    public Flux<Purchase> getPurchasesNoDetail(){
        return purchaseRepository.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<Purchase> getPurchaseById(String id){
        return  purchaseRepository.findById(id)
                .flatMap(purchase -> productRepository.findById(purchase.getProduct().getId())
                        .map(product -> {
                            purchase.setProduct(product);
                            return purchase;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Purchase> getPurchaseByIdNoDetail(String id){
        return purchaseRepository.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Purchase> updatePurchase(PurchaseDto purchaseDto, String id){
        return purchaseRepository.findById(id)
                .flatMap(purchase -> {
                    purchase.setPrice(purchaseDto.getPrice());
                    purchase.setQuantity(purchaseDto.getQuantity());
                    purchase.setEntryDateTime(purchaseDto.getEntryDateTime());

                    Product product = new Product();
                    product.setId(purchaseDto.getProductId());
                    purchase.setProduct(product);

                    return purchaseRepository.save(purchase);
        });
    }

    public  Mono<Void> deletePurchase(String id){
        return purchaseRepository.deleteById(id);
    }
}
