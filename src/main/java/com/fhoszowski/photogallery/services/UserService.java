package com.fhoszowski.photogallery.services;

import com.fhoszowski.photogallery.models.User;
import com.fhoszowski.photogallery.repositories.UserRepository;
import com.fhoszowski.photogallery.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername( String username ) {
        User user = userRepository.findByLogin(username);
        if (user == null)
            throw new UsernameNotFoundException("User does not exist!");
        else
            return new UserPrincipal(user);

    }

    public void addUser( User newUser ) {
        String password = newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setPassword(password);
        userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<String> getUsersLogin() {
        return userRepository.findAll()
                             .stream()
                             .map(user -> user.getLogin())
                             .collect(Collectors.toList());
    }

    User getUser( String username ) {
        return userRepository.findByLogin(username);
    }
}
