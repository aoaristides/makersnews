package br.com.makersweb.makersnews.email.infrastructure;

import br.com.makersweb.makersnews.email.domain.EmailSenderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author aaristides
 */
@Slf4j
public class FakeEmailSenderService extends SmtpEmailSenderService {

    @Override
    public void send(EmailSenderService.Message message) {
        String body = processTemplate(message);

        log.info("[FAKE E-MAIL] To: {}\n{}", message.getRecipient(), body);
    }

}
