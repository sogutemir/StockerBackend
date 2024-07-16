package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.ProductField;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFieldRepository extends ReactiveCrudRepository<ProductField, String > {
}
