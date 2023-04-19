package com.example.internservice.dto.intern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InternDTOUpdate {

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private String password;

    public InternDTOUpdate(String name, Integer age, String phone, String email, String password) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    
}
