package com.webstore.common.model.userprofile;

import com.webstore.common.model.Country;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by oles on 7/29/2016.
 */
@Entity
@Table(schema = "test_db_data", name = "user_profile")
@Data
@NoArgsConstructor(force = true)
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "up_id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "up_country_id")
    private Country country;
    @Column(name = "up_first_name")
    private String firstName;
    @Column(name = "up_last_name")
    private String lastName;
    @Column(name = "up_city")
    private String city;
    @Column(name = "up_birth_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime birthDate;

    /**
     * Constructor for mandatory fields.
     *
     * @param country
     * @param firstName
     * @param lastName
     */
    public UserProfile(Country country, String firstName, String lastName) {
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
