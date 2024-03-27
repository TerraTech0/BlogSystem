package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addAllUsers(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User u = userRepository.findUserByUserId(id);
        if (u == null){
            throw new ApiException("id not found!");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserByUserId(id);
        if (user == null){
            throw new ApiException("user not found!");
        }
        userRepository.delete(user);
    }

    public User checkValidUser(String username, String password){
        User user = userRepository.checkUserAndPass(username, password);
        if (user == null){
            throw new ApiException("user not valid!");
        }
        return user;
    }

    public User findUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new ApiException("user not found!");
        }
        return user;
    }

    public User findUserByUsername(String username){
        User user = userRepository.findUserByUsername(username);
        if (user == null){
            throw new ApiException("user not found!");
        }
        return user;
    }

    public List<User> findUserByRegisterDate(Date date){
        List<User> users = userRepository.findUsersByRegistration_date(date);
        if (users.isEmpty()){
            throw new ApiException("users not found!");
        }
        return users;
    }
}
