package br.com.makersweb.makersnews.password.exception;

import br.com.makersweb.makersnews.common.exception.BusinessException;

/**
 * @author aaristides
 */
public class ExpiredTokenException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ExpiredTokenException() {
        super("O token de redefinição de senha expirou");
    }

}
