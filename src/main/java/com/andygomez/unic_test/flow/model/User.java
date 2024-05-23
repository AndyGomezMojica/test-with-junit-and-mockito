package com.andygomez.unic_test.flow.model;

import com.andygomez.unic_test.flow.web.model.Role;
import com.andygomez.unic_test.flow.web.model.UserInput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
@Slf4j
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "is_active")
    private Boolean isActive;

    public static User userToInput(UserInput input){
        return User.builder()
                .userName(createUserName(input))
                .name(input.getName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .password(input.getPassword())
                .role(Role.USER)
                .createdAt(new Date())
                .createdBy(ADMIN)
                .isActive(true)
                .build();
    }

    public static String createUserName(UserInput input){

        if (input == null) throw new AssertionError();
        String inputName = input.getName().substring(0, 1);
        String inputUserName = inputName + input.getLastName().toUpperCase();

        log.info("Username created: {}", inputUserName);
        return inputUserName;
    }

}
