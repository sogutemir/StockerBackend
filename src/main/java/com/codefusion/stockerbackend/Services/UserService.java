package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.UserDto;
import com.codefusion.stockerbackend.Models.StockerType;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Repositories.StockerTypeRepository;
import com.codefusion.stockerbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final StockerTypeRepository stockerTypeRepo;

    public Mono<User> createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        StockerType stockerType = new StockerType();
        stockerType.setId(userDto.getStockerTypeId());

        user.setStockerType(stockerType);

        return userRepo.save(user);
    }

    public Flux<User> getUsers() {
        return userRepo.findAll()
                .flatMap(user -> stockerTypeRepo.findById(user.getStockerType().getId())
                        .map(stockerType -> {
                            user.setStockerType(stockerType);
                            return user;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }
    public Flux<User> getUsersNoDetail(){
        return userRepo.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<User> getUserById(String id) {
        return userRepo.findById(id)
                .flatMap(user -> stockerTypeRepo.findById(user.getStockerType().getId())
                        .map(stockerType -> {
                            user.setStockerType(stockerType);
                            return user;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<User> getUserByIdNoDetail(String id) {
        return userRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<User> updateUser(UserDto userDto, String id){
        return userRepo.findById(id)
                .flatMap(user -> {
                    user.setName(userDto.getName());
                    user.setSurname(userDto.getSurname());
                    user.setEmail(userDto.getEmail());
                    user.setPhone(userDto.getPhone());

                    StockerType stockerType = new StockerType();
                    stockerType.setId(userDto.getStockerTypeId());

                    user.setStockerType(stockerType);
                    return userRepo.save(user);
                });
    }

    public Mono<Void> deleteUser(String id) {
        return userRepo.deleteById(id);
    }
}
