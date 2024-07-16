package com.codefusion.stockerbackend.Dto;

import com.codefusion.stockerbackend.Models.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseDto {

    private Long quantity;
    private Long price;
    private LocalDateTime entryDateTime;
    private String productId;
}
