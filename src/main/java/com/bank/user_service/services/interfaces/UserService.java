package com.bank.user_service.services.interfaces;

import com.bank.user_service.dto.UserDTO;

import com.bank.user_service.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
    void addUser(UserDTO user);
    void deleteById(Long id);
    void editUser(UserDTO user,Long id);
    public List<User> getAll();
}
