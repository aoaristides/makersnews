package br.com.makersweb.makersnews.email.infrastructure;

import br.com.makersweb.makersnews.email.config.EmailProperties;
import br.com.makersweb.makersnews.email.domain.EmailException;
import br.com.makersweb.makersnews.email.domain.EmailSenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author aaristides
 */
@NoArgsConstructor
public class SmtpEmailSenderService implements EmailSenderService {

    private JavaMailSender mailSender;
    private EmailProperties emailProperties;
    private Configuration freemarkerConfig;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = createMimeMessage(message);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        String body = processTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getFrom());
        helper.setTo(message.getRecipient().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        return mimeMessage;
    }

    protected String processTemplate(Message message) {
        try {
            Template template = freemarkerConfig.getTemplate(message.getBody());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    template, message.getData());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }

}
