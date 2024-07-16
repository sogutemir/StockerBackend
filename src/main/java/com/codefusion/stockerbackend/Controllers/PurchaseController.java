package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.PurchaseDto;
import com.codefusion.stockerbackend.Models.Purchase;
import com.codefusion.stockerbackend.Services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping
    public Mono<Purchase> createPurchases(@RequestBody PurchaseDto purchaseDto){
        return service.createPurchase(purchaseDto);
    }

    @GetMapping
    public Flux<Purchase> getAllPurchasesND(){
        return service.getPurchasesNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<Purchase> getAllPurchases(){
        return service.getPurchases();
    }

    @GetMapping("/{id}")
    public Mono<Purchase> getPurchaseND(@PathVariable String id){
        return service.getPurchaseByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<Purchase> getPurchase(@PathVariable String id){
        return service.getPurchaseByIdNoDetail(id);
    }

    @PutMapping("/{id}")
    public Mono<Purchase> updatePurchase(@RequestBody PurchaseDto purchaseDto, @PathVariable String id){
        return service.updatePurchase(purchaseDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePurchase(@PathVariable String id){
        return service.deletePurchase(id);
    }
}
