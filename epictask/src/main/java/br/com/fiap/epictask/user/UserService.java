package br.com.fiap.epictask.user;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(OAuth2User principal){
        String email = principal.getAttributes().get("email").toString();
        var user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            return userRepository.save(new User(principal));
        }
        return user.get();

    }


    public void addScore(User user, int score) {
        user.setScore(user.getScore() + score);
        userRepository.save(user);
    }
}
