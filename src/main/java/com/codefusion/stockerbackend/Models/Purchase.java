package com.codefusion.stockerbackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Purchase {

    @Id
    private String id;
    private Product product;
    private Long quantity;
    private Long price;
    private LocalDateTime entryDateTime;
}

