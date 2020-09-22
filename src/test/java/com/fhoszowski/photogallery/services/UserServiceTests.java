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
    User admin;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setRole("USER");

        admin = new User();
        admin.setLogin("admin");
        admin.setPassword("password");
        admin.setRole("ADMIN");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldGetUser() {
        //given -> setUp
        //when
        when(userRepository.findByLogin("user")).thenReturn(user);
        //then
        assertEquals("user",userService.getUser("user").getLogin(),"should add user");

    }

    @Test
    void shouldHaveAdminRole() {
        //given -> setUp
        //when
        when(userRepository.findByLogin("admin")).thenReturn(admin);
        //then
        assertEquals("ADMIN",userService.getUser("admin").getRole(),"user should have admin role");

    }

}





