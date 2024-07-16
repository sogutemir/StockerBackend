package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.StockerTypeDto;
import com.codefusion.stockerbackend.Dto.UserAccountDto;
import com.codefusion.stockerbackend.Models.StockerType;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Models.UserAccount;
import com.codefusion.stockerbackend.Repositories.StockerTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockerTypeService {

    private final StockerTypeRepository stockerTypeRepo;

    public Mono<StockerType> createStockerType(StockerTypeDto stockerTypeDto) {
        StockerType stockerType = new StockerType();
        stockerType.setType(stockerTypeDto.getType());

        return stockerTypeRepo.save(stockerType);
    }

    public Flux<StockerType> getStockerTypes() {
        return stockerTypeRepo.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<StockerType> getStockerTypeById(String id) {
        return stockerTypeRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<StockerType> updateStockerType(StockerTypeDto stockerTypeDto, String id){
        return stockerTypeRepo.findById(id)
                .flatMap(stockerType -> {
                    stockerType.setType(stockerTypeDto.getType());
                    return stockerTypeRepo.save(stockerType);
                });
    }

    public Mono<Void> deleteStockerType(String id) {
        return stockerTypeRepo.deleteById(id);
    }

}
