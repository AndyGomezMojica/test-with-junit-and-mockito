package com.andygomez.unic_test.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String userName;
    private String name;
    private String lastName;
    private String email;

    public static UserResponse inputToResponse(UserInput input){
        return UserResponse.builder()
                .name(input.getName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .build();
    }

}
