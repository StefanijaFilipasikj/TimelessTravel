package mk.ukim.finki.historicLandmarks.service;

import mk.ukim.finki.historicLandmarks.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, String photoUrl);
    void deleteAllData();
    void addInitialData();

    boolean empty();
}