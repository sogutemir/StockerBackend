package com.codefusion.stockerbackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private Long quantity;
    private Long cost;
    private User user;


}
