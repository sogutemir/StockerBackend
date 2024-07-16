package com.codefusion.stockerbackend.Dto;

import lombok.Data;

@Data
public class AddressDto {

    private String city;
    private String district;
    private String street;
    private String neighborhood;
    private String userId;
}
