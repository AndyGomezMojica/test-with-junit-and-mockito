package com.andygomez.unic_test;

import com.andygomez.unic_test.application.UserUseCase;
import com.andygomez.unic_test.utils.exceptions.ValidationException;
import com.andygomez.unic_test.model.User;
import com.andygomez.unic_test.model.repository.UserRepository;
import com.andygomez.unic_test.web.model.UserInput;
import com.andygomez.unic_test.web.model.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserUseCase useCase;

    @Test
    public void testCreateUser() throws ValidationException{
        UserInput input = new UserInput("Andy", "Gomez", "a@a.a", "password");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

        UserResponse response = useCase.createUser(input);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

        Assertions.assertEquals(input.getName(), response.getName());
        Assertions.assertEquals(input.getLastName(), response.getLastName());
        Assertions.assertEquals(input.getEmail(), response.getEmail());

    }

    @Test
    public void testCreateUserWithNullFields() throws ValidationException{
        UserInput input = new UserInput(null, "Gomez", "a@a.a", "password");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

        UserResponse response = useCase.createUser(input);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

        Assertions.assertEquals(input.getName(), response.getName());
        Assertions.assertEquals(input.getLastName(), response.getLastName());
        Assertions.assertEquals(input.getEmail(), response.getEmail());

    }

    @Test
    public void testCreateUserWithEmailExists() throws ValidationException{

        User userWithEmail = new User();
        userWithEmail.setEmail("a@a.a");

        UserInput input = new UserInput("Andy", "Gomez", "a@a.a", "password");

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(userWithEmail);

        UserResponse response = useCase.createUser(input);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

        Assertions.assertEquals(input.getName(), response.getName());
        Assertions.assertEquals(input.getLastName(), response.getLastName());
        Assertions.assertEquals(input.getEmail(), response.getEmail());

    }


}
