package com.deepanshu.helpdeks.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;


@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_name")
    @NotBlank(message = "Username Cannot be empty")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "Password Cannot be empty")
    private String password;

    @Column(nullable = false, name = "user_role")
    @NotBlank(message = "Role Cannot be empty")
    private String role;
}
