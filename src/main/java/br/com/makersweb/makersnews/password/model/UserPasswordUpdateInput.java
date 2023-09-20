package br.com.makersweb.makersnews.password.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author aaristides
 */
@Getter
@Setter
public class UserPasswordUpdateInput {

    @NotBlank
    private String password;


}
