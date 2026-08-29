package com.SpringToDatabase_JPA.SpringToDatabase_JPA.controller;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateUserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.UserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
     private final UserService userService;

//
//     @PostMapping("/create")
//     public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto){
//          return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(createUserDto));
//     }

//
//     //USER own profile
//     @GetMapping("/me")
//     public ResponseEntity<UserDto> getMe() {
//          return ResponseEntity.ok(userService.getCurrentUser());
//     }
//
//     @PutMapping("/me")
//     public ResponseEntity<UserDto> updateMe(@RequestBody CreateUserDto dto) {
//          return ResponseEntity.ok(userService.updateCurrentUser(dto));
//     }
//
//
//     // ADMIN  get ALL USERS
//     @GetMapping
//     public ResponseEntity<List<UserDto>> getAllUsers(){
//          return ResponseEntity.ok(userService.getUsers());
//     }
//
////
//     @GetMapping("/paginate")
//     public ResponseEntity<List<UserDto>> getUsersPaginated(@RequestParam int page,
//                                                            @RequestParam int pageSize,
//                                                            @RequestParam(defaultValue = "asc") String direction,
//                                                            @RequestParam(defaultValue = "name") String sortBy ){
//          return ResponseEntity.status(HttpStatus.OK).body(userService.getUserPaginated(page,pageSize,direction,sortBy));
//     }



     //ADMIN specific user
//     @GetMapping("/{id}")
//     public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
//          return ResponseEntity.ok(userService.getUserById(id));
//
//     }
//     //ADMIN delete user
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
//          userService.deleteUserById(id);
//          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//     }
////
//     @PatchMapping("/{id}")
//     public ResponseEntity<UserDto> patchUser(@PathVariable Long id , @RequestBody CreateUserDto patchUserDto){
//          return ResponseEntity.status(HttpStatus.OK).body(userService.patchUser(id,patchUserDto));
//     }









     // USER own profile
     @GetMapping("/me")
     public ResponseEntity<UserDto> getMe() {
          return ResponseEntity.ok(userService.getCurrentUser());
     }

     @PutMapping("/me")
     public ResponseEntity<UserDto> updateMe(@RequestBody CreateUserDto dto) {
          return ResponseEntity.ok(userService.updateCurrentUser(dto));
     }

     // ADMIN get ALL USERS
     @GetMapping
     public ResponseEntity<List<UserDto>> getAllUsers(){
          return ResponseEntity.ok(userService.getUsers());
     }

     // ADMIN specific user
     @GetMapping("/{id}")
     public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
          return ResponseEntity.ok(userService.getUserById(id));
     }

     // ADMIN delete user
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
          userService.deleteUserById(id);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }



}
