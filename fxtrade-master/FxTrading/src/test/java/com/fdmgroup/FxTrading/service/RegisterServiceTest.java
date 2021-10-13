package com.fdmgroup.FxTrading.service;

import com.fdmgroup.FxTrading.error.UniqueEmailException;
import com.fdmgroup.FxTrading.model.SecurityQuestion;
import com.fdmgroup.FxTrading.model.User;
import com.fdmgroup.FxTrading.repository.UserRepository;
import com.fdmgroup.FxTrading.service.register.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

public class RegisterServiceTest {

    RegisterService registerService;

    @Mock
    UserRepository userRepository;

    @Mock
    ApplicationContext applicationContext;

    @Before
    public void init(){
        userRepository = Mockito.mock(UserRepository.class);
        applicationContext = Mockito.mock(ApplicationContext.class);
        registerService = new RegisterService(userRepository, applicationContext);
    }

    @Test
    public void testRegisterANewUser() throws UniqueEmailException {
        String firstName = "Bob";
        String lastName = "Jones";
        String email = "fakeemail@email.gov";
        String password = "Ppassword123!";
        List<SecurityQuestion> questions = null;
        User user = new User(firstName, lastName, email, password, questions);

        Optional<User> actual = registerService.registerNewUser(firstName, lastName, email, password, questions);

        Mockito.verify(userRepository, times(1)).findByEmail(email);
        assertEquals("Optional[" + user.toString() + "]", actual.toString());

    }

}
