package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.Purchase;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends ReactiveCrudRepository<Purchase, String> {
}
