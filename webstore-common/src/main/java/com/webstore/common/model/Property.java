package com.webstore.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by oler117 on 30.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "property")
@Data
@NoArgsConstructor(force = true)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer id;

    @Column(name = "p_key")
    private String key;

    @Column(name = "p_value")
    private String value;

}
