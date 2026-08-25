package com.SpringToDatabase_JPA.SpringToDatabase_JPA.service;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateUserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.UserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto saveUser(CreateUserDto createUserDto){
        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setName(createUserDto.getName());
        User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(),savedUser.getName(), savedUser.getEmail());

    }

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users){
            UserDto userDto = new UserDto(user.getId(),user.getName(),user.getEmail());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserDto(user.getId(),user.getName(),user.getEmail());
    }

    public UserDto patchUser(Long id, CreateUserDto patchUserDto) {
        User user = userRepository.findById(id).orElseThrow();

        if (patchUserDto.getName() != null){
            user.setName(patchUserDto.getName());
        }
        if (patchUserDto.getEmail() != null){
            user.setEmail(patchUserDto.getEmail());
        }

        return new UserDto(user.getId(),user.getName(), user.getEmail());
    }

    @Transactional
    public UserDto updateUser(Long id, CreateUserDto updateUserDto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(updateUserDto.getEmail());
        user.setName(updateUserDto.getName());

        return new UserDto(user.getId(), user.getEmail(), user.getName());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public  List<UserDto> getUserPaginated(int page, int pageSize, String direction, String sortBy) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():
        Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDto> userDtoList = new ArrayList<>();
        userPage.forEach(user -> userDtoList.add(new UserDto(user.getId(),user.getName(),user.getEmail())));

        return userDtoList;
    }
}
