package com.bank.user_service.services.impl;

import com.bank.user_service.dto.CardDTO;
import com.bank.user_service.dto.UserDTO;

import com.bank.user_service.models.User;
import com.bank.user_service.repository.UserRepository;

import com.bank.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private static final String CARD_MODULE_URI = "http://localhost:8765/cardclient/cards";
    private final WebClient webClient;
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

    public UserDTO userToDTO(User user) {
        if(user == null) return null;
        UserDTO userDTO = new UserDTO(
                user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), null
        );
        userDTO.setCards(getCardsForUser(userDTO.getId()));
        return userDTO;
    }

    private Set<CardDTO> getCardsForUser(Long userId) {
        return webClient.get()
                /*.uri(uriBuilder -> uriBuilder
                        .path(CARD_MODULE_URI)
                        .queryParam("userId", userId)
                        .build())*/
                .uri(CARD_MODULE_URI + "?userId=" + userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<CardDTO>>() {})
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)))
                .block();
    }
}
