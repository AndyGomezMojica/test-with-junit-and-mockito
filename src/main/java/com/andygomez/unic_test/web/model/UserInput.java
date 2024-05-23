package com.andygomez.unic_test.web.model;

import com.andygomez.unic_test.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInput {

    private static final String ADMIN = "ADMIN";

    private String name;
    private String lastName;
    private String email;
    private String password;

    public UserInput userToInput(User user){
        return UserInput.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
