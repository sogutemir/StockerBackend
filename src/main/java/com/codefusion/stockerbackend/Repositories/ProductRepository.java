package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, String> {
}
