package com.bank.user_service.services;

import com.bank.user_service.dto.UserDTO;

import com.bank.user_service.models.User;
import com.bank.user_service.repository.UserRepository;
import com.bank.user_service.services.interfaces.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (NullPointerException e)
        {
            throw new NullPointerException("Пользователь не найден"); //добавить ошибку
        }

    }

    @Override
    public void addUser(UserDTO user) {
        User userToSave = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();

        userRepository.save(userToSave);
        log.info("Add new user. Id: {}", userToSave.getId());
        System.err.println("Добавлен");

    }

    @Override
    public void deleteById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            userRepository.delete(user.get());
            log.info("Delete user. Id: {}", id);
        }catch (NullPointerException e)
        {
            throw new NullPointerException("Пользователь не найден"); //добавить ошибку
        }

    }

    @Override
    public void editUser(UserDTO editUser,Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            user.of(editUser);
            userRepository.save(user.get());
            log.info("Update user. Id: {}", id);

        }catch (NullPointerException e)
        {
            throw new NullPointerException("Пользователь не найден"); //добавить ошибку
        }

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
