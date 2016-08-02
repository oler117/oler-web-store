package com.webstore.common.repository.jpa;

import com.webstore.common.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oles on 7/29/2016.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
