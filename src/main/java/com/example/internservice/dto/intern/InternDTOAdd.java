package com.example.internservice.dto.intern;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InternDTOAdd {

    private String name;

    @Min(value = 18, message = "Intern age must be greater or equal to 18")
    @Max(value = 24, message = "Intern age cannot exceed 24")
    private int age;

    @NotBlank(message = "phone can not be empty")
    @Length(min = 10 ,max = 10 , message = "Not a valid phone number")
    private String phone;

    @Email(message = "Email is not valid")
    @NotBlank(message = "email can not be empty")
    private String email;
    
    @NotBlank(message = "password can not be empty")
    private String password;

    @Min(value = 0, message = "Intern age must be greater or equal to 18")
    @Max(value = 1, message = "Intern age cannot exceed 24")
    private int roleValue;

}
