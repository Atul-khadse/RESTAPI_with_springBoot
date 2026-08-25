package com.SpringToDatabase_JPA.SpringToDatabase_JPA.repository;

import com.SpringToDatabase_JPA.SpringToDatabase_JPA.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;


public interface UserRepository extends JpaRepository<User,Long> {

//    @Override
//    Page<User> findAll(Pageable pageable);

}
