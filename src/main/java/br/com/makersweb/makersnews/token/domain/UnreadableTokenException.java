package br.com.makersweb.makersnews.token.domain;

/**
 * @author aaristides
 */
public class UnreadableTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnreadableTokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
