package br.com.fiap.epictask.auth;


import br.com.fiap.epictask.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        OAuth2User principal = (OAuth2User) event.getAuthentication().getPrincipal();
        log.info("Logado com usuário " + principal);
        userService.register(principal);
    }
}

