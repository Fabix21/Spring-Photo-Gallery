package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setRole("USER");

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetUser() {
        //given -> setUp

        //when
        when(userRepository.findByLogin("user")).thenReturn(user);
        //then
        assertEquals(userService.getUser("user").getLogin(),"user");

    }

}





