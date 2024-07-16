package com.codefusion.stockerbackend.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class UserAccount {

    @Id
    private String id;
    private String username;
    private String password;
    private User user;
}
