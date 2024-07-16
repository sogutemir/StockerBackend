package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.UserDto;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public Mono<User> createUser(@RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

    @GetMapping
    public Flux<User> getAllUsersND(){
        return service.getUsersNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<User> getAllUsers(){
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> getUserND(@PathVariable String id){
        return service.getUserByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<User> getUser(@PathVariable String id){
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@RequestBody UserDto userDto, @PathVariable String id){
        return service.updateUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id){
        return service.deleteUser(id);
    }
}
