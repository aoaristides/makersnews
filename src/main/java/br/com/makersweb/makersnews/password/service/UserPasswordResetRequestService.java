package br.com.makersweb.makersnews.password.service;

import br.com.makersweb.makersnews.email.domain.EmailSenderService;
import br.com.makersweb.makersnews.token.domain.PasswordResetTokenService;
import br.com.makersweb.makersnews.user.domain.UserEntity;
import br.com.makersweb.makersnews.user.service.UserCrudService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
public class UserPasswordResetRequestService {

    private final PasswordResetTokenService passwordResetTokenService;
    private final UserCrudService userCrudService;
    private final EmailSenderService emailSenderService;
    private final ConversionService conversionService;

    public void requestResetting(String userEmail) {
        final var user = userCrudService.findByEmailOrFail(userEmail);

        var tokenPayload = conversionService.convert(user, PasswordResetTokenService.TokenPayload.class);

        String token = passwordResetTokenService.makeToken(tokenPayload);
        sendEmail(user, token);
    }

    private void sendEmail(UserEntity user, String token) {
        var message = EmailSenderService.Message.builder()
                .subject("AlgaNews - Redefinição de senha")
                .body("users/user-password-reset.html")
                .data("userName", user.getName())
                .data("resetUrl", makeUrl(user.getId(), token))
                .to(user.getEmail())
                .build();

        emailSenderService.send(message);
    }

    private String makeUrl(Long userId, String token) {
        return passwordResetTokenService.getResetUrl()
                .replace("{token}", token);
    }

}
