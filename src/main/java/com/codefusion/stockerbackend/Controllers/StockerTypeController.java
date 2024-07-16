package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.StockerTypeDto;
import com.codefusion.stockerbackend.Models.StockerType;
import com.codefusion.stockerbackend.Services.StockerTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stockertypes")
public class StockerTypeController {

    private final StockerTypeService service;

    @PostMapping
    public Mono<StockerType> createStockerType(@RequestBody StockerTypeDto stockerTypeDto) {
        return service.createStockerType(stockerTypeDto);
    }

    @GetMapping
    public Flux<StockerType> getStockerTypes() {
        return service.getStockerTypes();
    }

    @PutMapping("/{id}")
    public Mono<StockerType> updateStockerType(@RequestBody StockerTypeDto stockerTypeDto, @PathVariable String id) {
        return service.updateStockerType(stockerTypeDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteStockerType(@PathVariable String id) {
        return service.deleteStockerType(id);
    }
}
