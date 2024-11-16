package com.seg7.backendproject;

import java.util.ArrayList;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "user")
@Data
public class User {
    @Id
    private String ID;
    private String password;
    private String email;
}
