package br.com.makersweb.makersnews.email.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author aaristides
 */
@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("makersnews.email")
public class EmailProperties {

    private Impl impl = Impl.FAKE;

    @NotNull
    private String from;

    private Sandbox sandbox = new Sandbox();

    public enum Impl {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {

        private String to;

    }

}
