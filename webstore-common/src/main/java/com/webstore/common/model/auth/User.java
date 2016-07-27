package com.webstore.common.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oler117 on 26.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "user")
@SequenceGenerator(schema = "test_db_data", name = "user_u_id_seq")
@Data
@NoArgsConstructor(force = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_u_id_seq")
    @Column(name = "u_id")
    private Integer id;
    @Column(name = "u_username")
    private String username;
    @Column(name = "u_password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "test_db_data", name = "user_2_role",
            joinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "u_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "r_id")}
    )
    private List<Role> roles;

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        roles = user.getRoles();
    }

}
