package br.com.makersweb.makersnews.email.infrastructure;

import br.com.makersweb.makersnews.email.config.EmailProperties;
import lombok.NoArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author aaristides
 */
@NoArgsConstructor
public class SandboxEmailSenderService extends SmtpEmailSenderService {

    private EmailProperties emailProperties;

    @Override
    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        MimeMessage mimeMessage = super.createMimeMessage(message);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getTo());

        return mimeMessage;
    }

}
