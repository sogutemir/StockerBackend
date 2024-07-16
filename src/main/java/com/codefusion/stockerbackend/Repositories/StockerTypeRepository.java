package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.StockerType;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockerTypeRepository extends ReactiveCrudRepository<StockerType, String> {
}
