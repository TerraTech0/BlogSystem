package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId(Integer id);

    //get user by email
    //check for the user and password
    //get user by username
    //get user by register date


    User findUserByEmail(String email);

    @Query("select u from User u where u.username=?1 and u.password=?2")
    User checkUserAndPass(String username, String password);

    User findUserByUsername(String username);

    @Query("select u from User u where u.registration_date=?1")
    List<User> findUsersByRegistration_date(Date date);

}
