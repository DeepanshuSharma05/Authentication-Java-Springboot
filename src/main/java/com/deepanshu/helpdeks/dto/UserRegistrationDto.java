package com.deepanshu.helpdeks.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDto {

    @NotBlank(message = "Username Cannot be empty")
    private String userName;

    @NotBlank(message = "Password Cannot be empty")
    private String password;

    @NotBlank(message = "Role Cannot be empty")
    @Pattern(regexp = "^(CONSUMER|SERVICE_PROVIDER)", message = "Invalid account type selected ")
    private String accountType;

}
