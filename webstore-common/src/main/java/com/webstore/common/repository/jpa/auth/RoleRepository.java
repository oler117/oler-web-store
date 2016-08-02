package com.webstore.common.repository.jpa.auth;

import com.webstore.common.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oler117 on 26.07.2016.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
