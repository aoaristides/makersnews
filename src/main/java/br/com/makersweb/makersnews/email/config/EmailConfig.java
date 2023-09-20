package br.com.makersweb.makersnews.email.config;

import br.com.makersweb.makersnews.email.domain.EmailSenderService;
import br.com.makersweb.makersnews.email.infrastructure.FakeEmailSenderService;
import br.com.makersweb.makersnews.email.infrastructure.SandboxEmailSenderService;
import br.com.makersweb.makersnews.email.infrastructure.SmtpEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class EmailConfig {

    private EmailProperties emailProperties;

    public EmailConfig(@Autowired EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public EmailSenderService envioEmailService() {
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEmailSenderService();
            case SMTP:
                return new SmtpEmailSenderService();
            case SANDBOX:
                return new SandboxEmailSenderService();
            default:
                return null;
        }
    }

}
