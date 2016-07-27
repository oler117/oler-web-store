package com.webstore.common.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by oler117 on 26.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "role")
@SequenceGenerator(schema = "test_db_data", name = "role_r_id_seq")
@Data
@NoArgsConstructor(force = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_r_id_seq")
    @Column(name = "r_id")
    private Integer id;
    @Column(name = "r_rolename")
    private String roleName;
}
