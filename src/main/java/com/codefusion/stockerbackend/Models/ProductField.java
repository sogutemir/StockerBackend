package com.codefusion.stockerbackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ProductField {

    @Id
    private String id;
    private Product product;
    private String name;
    private String value;
}
