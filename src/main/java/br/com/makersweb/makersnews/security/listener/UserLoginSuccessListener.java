package br.com.makersweb.makersnews.security.listener;

import br.com.makersweb.makersnews.core.AuthUser;
import br.com.makersweb.makersnews.user.service.UserLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
@AllArgsConstructor
@Slf4j
public class UserLoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserLogService userLogService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        try {
            registerUserLogin(event);
        } catch (Exception e) {
            log.error("Ocorreu um erro ao tentar registrar o login do usuário.", e);
        }
    }

    private void registerUserLogin(AuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        if (isAuthClient(userDetails))
            return;

        userLogService.recordUserLogin(username);
    }

    private boolean isAuthClient(UserDetails userDetails) {
        return !(userDetails instanceof AuthUser);
    }
}
