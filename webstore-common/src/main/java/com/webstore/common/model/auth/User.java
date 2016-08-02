package com.webstore.common.model.auth;

import com.webstore.common.model.userprofile.UserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oler117 on 26.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "user")
@Data
@NoArgsConstructor(force = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer id;
    @Column(name = "u_username")
    private String username;
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_is_active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(schema = "test_db_data", name = "user_2_role",
            joinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "u_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id", referencedColumnName = "r_id")}
    )
    private List<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(schema = "test_db_data", name = "user_2_user_profile",
            joinColumns =
            @JoinColumn(name = "u2up_u_id", referencedColumnName = "u_id"),
            inverseJoinColumns =
            @JoinColumn(name = "u2up_up_id", referencedColumnName = "up_id"))
    private UserProfile userProfile;

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        roles = user.getRoles();
    }

    public User(String username, String password, List<Role> roles, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userProfile = userProfile;
    }


}
