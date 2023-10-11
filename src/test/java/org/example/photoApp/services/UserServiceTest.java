package org.example.photoApp.services;

import org.example.photoApp.models.User;
import org.example.photoApp.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void checkCreation_whenUserFound_thenFalse() {
        User user = new User();
        user.setName("Vasya");

        //Mockito.doReturn(new User().setName("Vasya")).when(userRepository).findByName("Vasya");
        Mockito.when(userRepository.findByName("Vasya")).thenReturn(new User().setName("Vasya"));
        var actual = userService.createUser(user);
        var excepted = false;
        assertEquals(excepted, actual);
    }

    @Test
    void checkCreation_whenUserNotFound_thenTrue() {
        User user = new User();
        user.setName("Vasya");
        user.setPassword("testPassword");

        Mockito.doReturn(null).when(userRepository).findByName("Vasya");
        Mockito.doReturn("dsfsdw123").when(passwordEncoder).encode("testPassword");
        Mockito.doReturn(null).when(userRepository).save(any());

        var actual = userService.createUser(user);
        var excepted = true;

        assertEquals(excepted, actual);
    }

    @Test
    void getUserByPrincipal_whenPrincipalIsNull_thenReturnNewUser(){
        Principal principal = null;

        User actual = userService.getUserByPrincipal(principal);

        assertNotNull(actual);
        assertNull(actual.getName());
    }

    @Test
    void getUserByPrincipal_whenPrincipalNotNull_thenReturnUserName(){

        Principal principal = Mockito.mock(Principal.class);

        User exceptedUser = new User().setName("Vasya");

        Mockito.when(principal.getName()).thenReturn("Vasya");
        Mockito.when(userRepository.findByName(principal.getName())).thenReturn(exceptedUser);

        User actual = userService.getUserByPrincipal(principal);

        assertEquals(exceptedUser.getName(), actual.getName());
    }
}