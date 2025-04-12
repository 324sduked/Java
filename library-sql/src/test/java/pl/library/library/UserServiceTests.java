package pl.library.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.library.library.entities.User;
import pl.library.library.repositories.UserRepository;
import pl.library.library.services.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    private User user;
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUser_id(2L);
        user.setFirst_name("TestName");
        user.setLast_name("TestLastName");
        user.setEmail("TestEmail");
    }
    private void VerifyUsersProperties(User user){
        assertNotNull(user);
        assertEquals("TestName", user.getFirst_name());
        assertEquals("TestLastName", user.getLast_name());
        assertEquals("TestEmail", user.getEmail());
        assertEquals(2L, user.getUser_id());
    }
    @Test
     void FindUserById() {
        Mockito.when(userRepository.findById(user.getUser_id())).thenReturn(Optional.of(user));
        User foundUser = userService.findById(user.getUser_id());
        VerifyUsersProperties(foundUser);
    }
    @Test
    void FindUserByEmail() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        User foundUser = userService.findByEmail(user.getEmail());
        VerifyUsersProperties(foundUser);
    }
    @Test
    void SaveUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);
        VerifyUsersProperties(savedUser);
    }
}
