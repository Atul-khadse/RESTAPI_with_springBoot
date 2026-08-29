package com.SpringToDatabase_JPA.SpringToDatabase_JPA.service;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateUserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.UserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.exception.UserNotFoundException;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getLoggedInUser() {
        String email = Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication()).getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserDto getCurrentUser() {
        User user = getLoggedInUser();
        return map(user);
    }

    @Transactional
    public UserDto updateCurrentUser(CreateUserDto dto) {
        User user = getLoggedInUser();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return map(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return map(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto map(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }







//
//    public UserDto saveUser(CreateUserDto createUserDto){
//        User user = new User();
//        user.setEmail(createUserDto.getEmail());
//        user.setName(createUserDto.getName());
//        User savedUser = userRepository.save(user);
//
//        return new UserDto(savedUser.getId(),savedUser.getName(), savedUser.getEmail());
//
//    }
//
//    public UserDto patchUser(Long id, CreateUserDto patchUserDto) {
//        User user = userRepository.findById(id).orElseThrow();
//
//        if (patchUserDto.getName() != null){
//            user.setName(patchUserDto.getName());
//        }
//        if (patchUserDto.getEmail() != null){
//            user.setEmail(patchUserDto.getEmail());
//        }
//
//        return new UserDto(user.getId(),user.getName(), user.getEmail());
//    }
//
//    @Transactional
//    public UserDto updateUser(Long id, CreateUserDto updateUserDto) {
//        User user = userRepository.findById(id).orElseThrow();
//        user.setEmail(updateUserDto.getEmail());
//        user.setName(updateUserDto.getName());
//
//        return new UserDto(user.getId(), user.getEmail(), user.getName());
//    }
//
//
//
//    public  List<UserDto> getUserPaginated(int page, int pageSize, String direction, String sortBy) {
//
//        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():
//        Sort.by(sortBy).descending();
//
//        Pageable pageable = PageRequest.of(page,pageSize,sort);
//        Page<User> userPage = userRepository.findAll(pageable);
//
//        List<UserDto> userDtoList = new ArrayList<>();
//        userPage.forEach(user -> userDtoList.add(new UserDto(user.getId(),user.getName(),user.getEmail())));
//
//        return userDtoList;
//    }



}
