package com.andygomez.unic_test.application;

import com.andygomez.unic_test.utils.exceptions.ValidationException;
import com.andygomez.unic_test.model.User;
import com.andygomez.unic_test.model.repository.UserRepository;
import com.andygomez.unic_test.web.model.UserInput;
import com.andygomez.unic_test.web.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserUseCase {

    @Autowired
    private UserRepository repository;

    @Transactional(rollbackFor = Throwable.class)
    public UserResponse createUser(UserInput input) throws ValidationException {

        try {
            User existentUser = repository.findByEmail(input.getEmail());

            if (validateEmail(existentUser)) {
                throw new ValidationException("Email already exists");
            }

            if (!validateFields(input)) {
                throw new ValidationException("Fields cannot be null");
            }

            User newUser = User.userToInput(input);

            repository.save(newUser);

            return UserResponse.inputToResponse(input);
        } catch (Exception e) {
            throw new ValidationException("Error creating user", e);
        }
    }

    private boolean validateFields(UserInput input) {
        return input.getName() != null && input.getLastName() != null && input.getEmail() != null && input.getPassword() != null;
    }

    private boolean validateEmail(User existentUser) {
        return existentUser != null;
    }
}
