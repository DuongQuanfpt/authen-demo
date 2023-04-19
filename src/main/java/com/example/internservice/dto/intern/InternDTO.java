package com.example.internservice.dto.intern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InternDTO {
    
    private long id;
    
    
    private String name;

    private int age;

    private String phone;

    private String email;

    private String password;

    private boolean isActive;

    private String role;

//    public InternDTO(long id, String name, int age, String phone, String email, String password, boolean isActive) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.phone = phone;
//        this.email = email;
//        this.password = password;
//        this.isActive = isActive;
//    }

    public InternDTO(long id, String name, int age, String phone, String email, String password, boolean isActive, String role) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }
}
