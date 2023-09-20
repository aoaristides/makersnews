package br.com.makersweb.makersnews.password.converter;

import br.com.makersweb.makersnews.token.domain.PasswordResetTokenService;
import br.com.makersweb.makersnews.user.domain.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
public class UserToTokenPayloadConverter implements Converter<UserEntity, PasswordResetTokenService.TokenPayload> {

    @Override
    public PasswordResetTokenService.TokenPayload convert(UserEntity user) {
        return PasswordResetTokenService.TokenPayload.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .lastLoginAt(user.getLastLogin())
                .build();
    }

}
