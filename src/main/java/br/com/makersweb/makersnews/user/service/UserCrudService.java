package br.com.makersweb.makersnews.user.service;

import br.com.makersweb.makersnews.user.domain.UserEntity;
import br.com.makersweb.makersnews.user.exception.UserNotFoundException;
import br.com.makersweb.makersnews.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
public class UserCrudService {

    private final UserRepository userRepository;

    public UserEntity findByEmailOrFail(String email) {
        return userRepository.findActivesByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        user = userRepository.save(user);
        return user;
    }

}
