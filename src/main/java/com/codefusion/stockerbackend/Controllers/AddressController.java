package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.AddressDto;
import com.codefusion.stockerbackend.Models.Address;
import com.codefusion.stockerbackend.Services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressService service;

    @PostMapping
    public Mono<Address> createAddress(@RequestBody AddressDto addressDto){
        return service.createAddress(addressDto);
    }

    @GetMapping
    public Flux<Address> getAllAddressesND() {
        return service.getAddressesNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<Address> getAllAddresses() {
        return service.getAddresses();
    }

    @GetMapping("/{id}")
    public Mono<Address> getAddressND(@PathVariable String id) {
        return service.getAddressByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<Address> getAddress(@PathVariable String id) {
        return service.getAddressById(id);
    }

    @PutMapping("/{id}")
    public Mono<Address> updateAddress(@RequestBody AddressDto addressDto, @PathVariable String id) {
        return service.updateAddress(addressDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAddress(@PathVariable String id) {
        return service.deleteAddress(id);
    }
}
