package com.codefusion.stockerbackend.Controllers;

import com.codefusion.stockerbackend.Dto.UserAccountDto;
import com.codefusion.stockerbackend.Models.UserAccount;
import com.codefusion.stockerbackend.Services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/useraccounts")
public class UserAccountController {

    private final UserAccountService service;

    @PostMapping
    public Mono<UserAccount> createUserAccount(@RequestBody UserAccountDto userAccountDto) {
        return service.createUserAccount(userAccountDto);
    }

    @GetMapping
    public Flux<UserAccount> getAllUserAccountsND() {
        return service.getUserAccountsNoDetail();
    }

    @GetMapping("/detailed")
    public Flux<UserAccount> getAllUserAccount() {
        return service.getUserAccounts();
    }

    @GetMapping("/{id}")
    public Mono<UserAccount> getUserAccountND(@PathVariable String id) {
        return service.getUserAccountByIdNoDetail(id);
    }

    @GetMapping("/detailed/{id}")
    public Mono<UserAccount> getUserAccount(@PathVariable String id) {
        return service.getUserAccountById(id);
    }

    @PutMapping("/{id}")
    public Mono<UserAccount> updateUserAccount(@RequestBody UserAccountDto userAccountDto, @PathVariable String id) {
        return service.updateUserAccount(userAccountDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUserAccount(@PathVariable String id) {
        return service.deleteUserAccount(id);
    }
}
