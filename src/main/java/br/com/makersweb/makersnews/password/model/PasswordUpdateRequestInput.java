package br.com.makersweb.makersnews.password.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author aaristides
 */
@Getter
@Setter
public class PasswordUpdateRequestInput {

    @Email
    @NotBlank
    private String email;

}
