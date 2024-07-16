package com.codefusion.stockerbackend.Dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String stockerTypeId;
}
