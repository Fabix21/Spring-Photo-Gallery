package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.exceptions.UsernameTakenException;
import com.fhoszowski.photogallery.models.Gallery;
import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTests {

    @Mock
    UserRepository userRepository;
    @Mock
    Principal principal;
    @Mock
    PasswordEncoder passwordEncoder;

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

        List<Gallery> gallery = new ArrayList<>();
        gallery.add(Gallery.builder().galleryname("Dogs").user(user).build());
        gallery.add(Gallery.builder().galleryname("Cats").user(user).build());
        user.setGalleries(gallery);


        admin = new User();
        admin.setLogin("admin");
        admin.setPassword("password");
        admin.setRole("ADMIN");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldAddUser() {
        //given -> setup
        //when
        when(userRepository.save(any(User.class))).thenReturn(user);
        //then
        userService.addUser(user);
        verify(userRepository,times(1)).save(user);
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
    void shouldThrowUsernameTakenException() {
        //given
        User usernameTaken = new User();
        usernameTaken.setLogin("user");
        usernameTaken.setPassword("password");

        //when
        when(userRepository.findByLogin("user")).thenReturn(null);
        when(userRepository.findByLogin("user")).thenReturn(user);

        //then
        assertThrows(UsernameTakenException.class,() -> {
            userService.addUser(user);
            userService.addUser(usernameTaken);
        });
    }


    @Test
    void shouldHaveAdminRole() {
        //given -> setUp
        //when
        when(userRepository.findByLogin("admin")).thenReturn(admin);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
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
        assertEquals(usersLogin,userService.getUsersLogin(),"should contain list of users login");
    }

    @Test
    void shouldGetUsersGalleries() {
        //given
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);

        List<String> userGalleries = new ArrayList<>();
        userGalleries.add("Dogs");
        userGalleries.add("Cats");

        //when
        when(principal.getName()).thenReturn("user");
        when(userService.getUser("user")).thenReturn(user);

        //then
        assertEquals(userGalleries,userService.getAvailableGalleriesNames(principal),"should contain list of user galleries");
    }
}





