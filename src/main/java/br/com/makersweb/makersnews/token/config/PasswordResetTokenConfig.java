package br.com.makersweb.makersnews.token.config;

import br.com.makersweb.makersnews.core.SecurityProperties;
import br.com.makersweb.makersnews.token.infrastructure.StatelessPasswordResetTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
@AllArgsConstructor
public class PasswordResetTokenConfig {

    private final SecurityProperties securityProperties;

    @Bean
    public StatelessPasswordResetTokenService passwordUpdate() {
        return new StatelessPasswordResetTokenService(securityProperties.getResetToken().getPasswordReset());
    }

}
