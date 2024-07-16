package com.codefusion.stockerbackend.Repositories;

import com.codefusion.stockerbackend.Models.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, String> {
}
