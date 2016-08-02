package com.webstore.common.repository.jpa;

import com.webstore.common.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oler117 on 30.07.2016.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    Property findByKey(String key);
}
