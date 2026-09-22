package com.deepanshu.helpdeks.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor    // Generates a constructor with 0 arguments
@AllArgsConstructor
@Builder

public class UserLoginDto {

    @NotBlank(message = "Username cannot be blank")
    public String userName;

    @NotBlank(message = "Password cannot be blank")
    public String password;
}
