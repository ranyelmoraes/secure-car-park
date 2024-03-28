package com.pitang.securecarpark.securecarpark.service;

import com.pitang.securecarpark.securecarpark.entity.Car;
import com.pitang.securecarpark.securecarpark.entity.User;
import com.pitang.securecarpark.securecarpark.exception.EmailUniqueViolationException;
import com.pitang.securecarpark.securecarpark.exception.EntityNotFoundException;
import com.pitang.securecarpark.securecarpark.exception.LoginUniqueViolationException;
import com.pitang.securecarpark.securecarpark.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        if (user.getCars() != null) {
            for (Car car : user.getCars()) {
                car.setUserId(user);
            }
        }
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {

            String errorMessage = Objects.requireNonNull(ex.getRootCause()).getMessage();
            if (errorMessage.contains("CONSTRAINT_INDEX_4D")) {
                throw new LoginUniqueViolationException("Login already exists");
            } else if (errorMessage.contains("CONSTRAINT_INDEX_4")) {
                throw new EmailUniqueViolationException("Email already exists");
            } else {
                throw new DataIntegrityViolationException("Data integrity violation occurred", ex);
            }
        }

    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );
    }

    @Transactional
    public User updateUser(User user, User updatedUser) {

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setBirthday(updatedUser.getBirthday());
        user.setLogin(updatedUser.getLogin());
        user.setPassword(updatedUser.getPassword());
        user.setPhone(updatedUser.getPhone());
        user.setCars(updatedUser.getCars());

        return userRepository.save(user);
    }


    @Transactional
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
