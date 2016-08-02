package com.webstore.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by oles on 7/29/2016.
 */
@Entity
@Table(schema = "test_db_data", name = "country")
@Data
@NoArgsConstructor(force = true)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer id;
    @Column(name = "c_iso_code")
    private String isoCode;
    @Column(name = "c_name")
    private String name;

}
