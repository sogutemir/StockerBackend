package com.codefusion.stockerbackend.Services;

import com.codefusion.stockerbackend.Dto.UserAccountDto;
import com.codefusion.stockerbackend.Models.User;
import com.codefusion.stockerbackend.Models.UserAccount;
import com.codefusion.stockerbackend.Repositories.UserAccountRepository;
import com.codefusion.stockerbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepo;
    private final UserRepository userRepo;

    public Mono<UserAccount> createUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setPassword(userAccountDto.getPassword());

        User user = new User();
        user.setId(userAccountDto.getUserId());

        userAccount.setUser(user);

        return userAccountRepo.save(userAccount);
    }

    public Flux<UserAccount> getUserAccounts() {
        return userAccountRepo.findAll()
                .flatMap(userAccount -> userRepo.findById(userAccount.getUser().getId())
                        .map(user -> {
                            userAccount.setUser(user);
                            return userAccount;
                        })
                )
                .switchIfEmpty(Flux.empty());
    }

    public Flux<UserAccount> getUserAccountsNoDetail(){
        return userAccountRepo.findAll()
                .switchIfEmpty(Flux.empty());
    }

    public Mono<UserAccount> getUserAccountById(String id) {
        return userAccountRepo.findById(id)
                .flatMap(userAccount -> userRepo.findById(userAccount.getUser().getId())
                        .map(user -> {
                            userAccount.setUser(user);
                            return userAccount;
                        })
                )
                .switchIfEmpty(Mono.empty());
    }

    public Mono<UserAccount> getUserAccountByIdNoDetail(String id) {
        return userAccountRepo.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<UserAccount> updateUserAccount(UserAccountDto userAccountDto, String id){
        return userAccountRepo.findById(id)
                .flatMap(userAccount -> {
                    userAccount.setUsername(userAccountDto.getUsername());
                    userAccount.setPassword(userAccountDto.getPassword());

                    User user = new User();
                    user.setId(userAccountDto.getUserId());

                    userAccount.setUser(user);

                    return userAccountRepo.save(userAccount);
                });
    }

    public Mono<Void> deleteUserAccount(String id) {
        return userAccountRepo.deleteById(id);
    }
}
