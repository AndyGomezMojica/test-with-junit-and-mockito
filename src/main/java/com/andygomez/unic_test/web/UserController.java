package com.andygomez.unic_test.web;

import com.andygomez.unic_test.application.UserUseCase;
import com.andygomez.unic_test.utils.exceptions.ValidationException;
import com.andygomez.unic_test.web.model.UserInput;
import com.andygomez.unic_test.web.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserUseCase useCase;

    @PostMapping("/saveUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserInput input) throws ValidationException {
        return ResponseEntity.ok(useCase.createUser(input));
    }
}
