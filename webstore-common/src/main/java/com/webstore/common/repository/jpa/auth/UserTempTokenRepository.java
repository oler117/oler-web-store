package com.webstore.common.repository.jpa.auth;

import com.webstore.common.model.auth.UserTempToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oler117 on 30.07.2016.
 */
@Repository
public interface UserTempTokenRepository extends JpaRepository<UserTempToken, Integer> {
    UserTempToken findByToken(String token);
}
