package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
