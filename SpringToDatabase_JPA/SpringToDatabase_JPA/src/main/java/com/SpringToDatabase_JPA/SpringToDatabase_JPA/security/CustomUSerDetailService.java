package com.SpringToDatabase_JPA.SpringToDatabase_JPA.security;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.exception.UserNotFoundException;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUSerDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with email: "+ email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

    }
}
