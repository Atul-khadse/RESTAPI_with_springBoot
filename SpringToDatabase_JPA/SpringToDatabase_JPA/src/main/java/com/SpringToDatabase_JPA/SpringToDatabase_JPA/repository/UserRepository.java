package com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {


}
