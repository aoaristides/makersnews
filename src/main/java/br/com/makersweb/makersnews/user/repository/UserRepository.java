package br.com.makersweb.makersnews.user.repository;

import br.com.makersweb.makersnews.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author aaristides
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("from UserEntity where email = :email and active = true")
    Optional<UserEntity> findActivesByEmail(String email);

}
