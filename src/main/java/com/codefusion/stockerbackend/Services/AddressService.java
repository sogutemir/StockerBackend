package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.AddressDto;
import com.codefusion.stockerbackend.Models.Address;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Repositories.AddressRepository;
import com.codefusion.stockerbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepo;
    private final UserRepository userRepo;

    public Mono<Address> createAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setDistrict(addressDto.getDistrict());
        address.setStreet(addressDto.getStreet());
        address.setNeighborhood(addressDto.getNeighborhood());

        User user = new User();
        user.setId(addressDto.getUserId());

        address.setUser(user);

        return addressRepo.save(address);
    }

    public Flux<Address> getAddresses() {
        return addressRepo.findAll()
                .flatMap(userAccount -> userRepo.findById(userAccount.getUser().getId())
                        .map(user -> {
                            userAccount.setUser(user);
                            return userAccount;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }

    public Flux<Address> getAddressesNoDetail(){
        return addressRepo.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<Address> getAddressById(String id) {
        return addressRepo.findById(id)
                .flatMap(userAccount -> userRepo.findById(userAccount.getUser().getId())
                        .map(user -> {
                            userAccount.setUser(user);
                            return userAccount;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Address> getAddressByIdNoDetail(String id) {
        return addressRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Address> updateAddress(AddressDto addressDto, String id){
        return addressRepo.findById(id)
                .flatMap(address -> {
                    address.setCity(addressDto.getCity());
                    address.setDistrict(addressDto.getDistrict());
                    address.setStreet(addressDto.getStreet());
                    address.setNeighborhood(addressDto.getNeighborhood());

                    User user = new User();
                    user.setId(addressDto.getUserId());

                    address.setUser(user);

                    return addressRepo.save(address);
                });
    }

    public Mono<Void> deleteAddress(String id) {
        return addressRepo.deleteById(id);
    }
}
