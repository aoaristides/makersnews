package br.com.makersweb.makersnews.core;

import br.com.makersweb.makersnews.user.domain.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author aaristides
 */
@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public AuthUser(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getEmail(), userEntity.getPassword(), userEntity.getActive(),
                true, true, true, authorities);
        this.id = userEntity.getId();
        this.name = userEntity.getName();
    }

}
