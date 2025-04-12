package pl.library.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.library.library.entities.User;
import pl.library.library.repositories.UserRepository;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> GetAllUsers() {
        return userRepository.findAll();
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findById(Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

}
