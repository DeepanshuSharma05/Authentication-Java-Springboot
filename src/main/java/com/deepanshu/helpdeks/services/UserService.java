package com.deepanshu.helpdeks.services;
import com.deepanshu.helpdeks.dto.UserLoginDto;
import com.deepanshu.helpdeks.dto.UserRegistrationDto;
import com.deepanshu.helpdeks.entities.User;
import com.deepanshu.helpdeks.repository.UserRepository;
import com.deepanshu.helpdeks.security.JwtUtil;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Getter
@Setter
@Builder
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;



    public User registerUser(UserRegistrationDto userRegistrationDto){

        if(userRepository.existsByUserName(userRegistrationDto.getUserName())){
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUserName(userRegistrationDto.getUserName());
        user.setPassword(userRegistrationDto.getPassword());
        user.setRole(userRegistrationDto.getAccountType());

        return userRepository.save(user);
    }

    public String loginUser(UserLoginDto userLoginDto){

        User user = userRepository.findByUserName(userLoginDto.getUserName())
                .orElseThrow(()-> new RuntimeException("Invalid username or password"));


        boolean isPasswordMatch = passwordEncoder.matches(userLoginDto.getPassword() ,user.getPassword());
        if (!isPasswordMatch){
            throw new RuntimeException("invalid username or password");
        }

        return jwtUtil.generateToken(user.getUserName());
    }
}
