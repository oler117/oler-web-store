package com.webstore.common.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by oler117 on 30.07.2016.
 */
@Entity
@Table(schema = "test_db_data", name = "user_temp_token")
@Data
@NoArgsConstructor(force = true)
public class UserTempToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utt_id")
    private Integer id;
    @Column(name = "utt_user_id")
    private Integer userId;
    @Column(name = "utt_token_type")
    @Enumerated(value = EnumType.STRING)
    private UserTempTokenType tokenType;
    @Column(name = "utt_token")
    private String token;

    public UserTempToken(Integer userId, UserTempTokenType tokenType, String token) {
        this.userId = userId;
        this.tokenType = tokenType;
        this.token = token;
    }
}
