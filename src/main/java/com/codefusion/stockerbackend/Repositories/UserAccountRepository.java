package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.UserAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, String> {
}
