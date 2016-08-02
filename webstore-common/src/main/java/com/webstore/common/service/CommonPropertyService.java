package com.webstore.common.service;

import com.google.common.base.Strings;
import com.webstore.common.model.Property;
import com.webstore.common.repository.jpa.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by oler117 on 30.07.2016.
 */
@Service
public class CommonPropertyService implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public String getProperty(String key) {
        Property property = propertyRepository.findByKey(key);
        return property.getValue();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String propertyValue = getProperty(key);
        return Strings.isNullOrEmpty(propertyValue) ? defaultValue : propertyValue;
    }
}
