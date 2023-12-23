package mk.ukim.finki.historicLandmarks.service.impl;

import mk.ukim.finki.historicLandmarks.model.User;
import mk.ukim.finki.historicLandmarks.model.enumerations.Role;
import mk.ukim.finki.historicLandmarks.model.exception.InvalidArgumentsException;
import mk.ukim.finki.historicLandmarks.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.historicLandmarks.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.historicLandmarks.repository.UserRepository;
import mk.ukim.finki.historicLandmarks.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findById(username).orElseThrow(InvalidUserCredentialsException::new);
    }
}
