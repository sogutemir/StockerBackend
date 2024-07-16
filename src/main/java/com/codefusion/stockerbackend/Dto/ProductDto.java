package com.codefusion.stockerbackend.Dto;

import com.codefusion.stockerbackend.Models.User;
import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private Long quantity;
    private Long cost;
    private String userId;
}
