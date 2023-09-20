package br.com.makersweb.makersnews.user.exception;

import javax.persistence.EntityNotFoundException;

/**
 * @author aaristides
 */
public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super(String.format("Não existe usuário com o id %s", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("Não existe usuário com o e-mail %s", email));
    }

}
