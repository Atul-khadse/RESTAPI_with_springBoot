package com.SpringToDatabase_JPA.SpringToDatabase_JPA.service;


import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateUserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.LoginDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.LoginResponseDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.RegisterUserResponseDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.Role;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.UserRepository;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private  final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterUserResponseDto registerUser(CreateUserDto createUserDto){
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.USER);

        User saveUser = userRepository.save(user);
        return new RegisterUserResponseDto(saveUser.getName(), saveUser.getId());
    }



    public LoginResponseDto login(LoginDto loginDto){
        log.info("Attempting login for user email: {}", loginDto.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            log.info("Authentication successful for user: {}", loginDto.getEmail());

            String jwtToken = jwtService.generateJwToken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));

            log.info("JWT Token generated successfully for user: {}", loginDto.getEmail());
            return new LoginResponseDto(jwtToken);

        } catch (AuthenticationException e) {
            // Catches BadCredentialsException, LockedException, DisabledException, etc.
            log.error("Authentication failed for user [{}]: {}", loginDto.getEmail(), e.getMessage());
            throw e;

        }
        catch (Exception e){
            log.error("Internal system error during login process for user [{}]: ", loginDto.getEmail(), e);
            throw e;
        }

    }

}
