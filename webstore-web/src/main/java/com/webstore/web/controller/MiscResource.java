package com.webstore.web.controller;

import com.webstore.common.model.Country;
import com.webstore.common.repository.jpa.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oles on 7/29/2016.
 */
@RestController
@RequestMapping(value = "/misc", method = RequestMethod.GET)
public class MiscResource {

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(value = "/countries")
    public ResponseEntity getAvailableCountries() throws Exception {

        final List<Country> availableCountries = countryRepository.findAll();
        if (availableCountries == null) {
//        throw new Exception("No coutries data!");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(availableCountries);
    }

}
