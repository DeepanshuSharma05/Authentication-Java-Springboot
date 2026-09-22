package com.deepanshu.helpdeks.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder

public class UserLoginDto {

    @NotBlank(message = "Username cannot be blank")
    public String userName;

    @NotBlank(message = "Password cannot be blank")
    public String password;
}
