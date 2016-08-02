package com.webstore.common.service;

/**
 * Created by oler117 on 30.07.2016.
 */
public interface PropertyService {

    String getProperty(String key);

    String getProperty(String key, String defaultValue);

}
