package br.com.makersweb.makersnews.user.service;

import br.com.makersweb.makersnews.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

/**
 * @author aaristides
 */
@Service
@AllArgsConstructor
public class UserLogService {

    private final UserCrudService userCrudService;

    public void recordUserLogin(String userName) {
        UserEntity userEntity = userCrudService.findByEmailOrFail(userName);
        userEntity.setLastLogin(OffsetDateTime.now());
        userCrudService.save(userEntity);
    }

}
