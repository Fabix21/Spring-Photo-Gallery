package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;
    User user1;
    User admin;


    @BeforeEach
    void setUp() {
        user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setRole("USER");

        user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("password");
        user1.setRole("USER");


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

    @Test
    void shouldGetUsersLogin() {
        //given & setUp
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        List<String> usersLogin = new ArrayList<>();
        usersLogin.add("user");
        usersLogin.add("user1");
        //when
        when(userRepository.findAll()).thenReturn(users);
        //then
        assertEquals(userService.getUsersLogin(),usersLogin,"should contains list of users login");
    }
}





