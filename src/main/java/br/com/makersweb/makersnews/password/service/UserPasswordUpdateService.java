package br.com.makersweb.makersnews.password.service;

import br.com.makersweb.makersnews.common.exception.BusinessException;
import br.com.makersweb.makersnews.password.exception.ExpiredTokenException;
import br.com.makersweb.makersnews.password.exception.InvalidTokenException;
import br.com.makersweb.makersnews.token.domain.PasswordResetTokenService;
import br.com.makersweb.makersnews.user.domain.UserEntity;
import br.com.makersweb.makersnews.user.service.UserCrudService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
public class UserPasswordUpdateService {

    private final PasswordResetTokenService passwordResetTokenService;
    private final UserCrudService userCrudService;
    private final PasswordEncoder passwordEncoder;
    private final ConversionService conversionService;

    @Transactional
    public void updateUsingToken(String newPassword, String token) throws InvalidTokenException {
        if (StringUtils.isBlank(newPassword)) {
            throw new BusinessException("A nova senha não pode estar em branco.");
        }

        final var user = userCrudService.findByEmailOrFail(passwordResetTokenService.extractUsername(token));

        final var tokenPayload = conversionService.convert(user, PasswordResetTokenService.TokenPayload.class);

        if (passwordResetTokenService.isExpired(token)) {
            throw new ExpiredTokenException();
        }

        if (passwordResetTokenService.isInvalid(tokenPayload, token)) {
            throw new InvalidTokenException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
    }

    public void checkExpiration(String token) throws InvalidTokenException {
        if (passwordResetTokenService.isExpired(token)) {
            throw new ExpiredTokenException();
        }
    }

}
