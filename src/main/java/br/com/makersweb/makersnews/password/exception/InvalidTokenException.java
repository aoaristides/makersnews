package br.com.makersweb.makersnews.password.exception;

import br.com.makersweb.makersnews.common.exception.BusinessException;

/**
 * @author aaristides
 */
public class InvalidTokenException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public InvalidTokenException() {
        super("O token de redefinição de senha não é válido");
    }

}
