package com.webstore.common.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by oler117 on 26.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "role")
@Data
@NoArgsConstructor(force = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer id;
    @Column(name = "r_rolename")
    private String name;

}
