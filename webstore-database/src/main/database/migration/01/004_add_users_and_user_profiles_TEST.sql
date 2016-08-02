--liquibase formatted sql

--changeset oles:add_users_and_user_profiles

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- ++++++++++++++++++++++++++++++++++++++ TEST DATA ++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

-- guest
INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('guest', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 1);
INSERT INTO test_db_data.user_profile (up_country_id, up_first_name, up_last_name, up_city, up_birth_date)
VALUES (1, 'FirstName1', 'LastName1', 'London', DATE '1990-01-01');
INSERT INTO test_db_data.user_2_user_profile (u2up_u_id, u2up_up_id)
VALUES ((SELECT max(u_id)
         FROM test_db_data.user), (SELECT max(up_id)
                                   FROM test_db_data.user_profile));

-- user
INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('user', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 2);
INSERT INTO test_db_data.user_profile (up_country_id, up_first_name, up_last_name, up_city, up_birth_date)
VALUES (1, 'FirstName2', 'LastName2', 'Edinburgh', DATE '1990-01-01');
INSERT INTO test_db_data.user_2_user_profile (u2up_u_id, u2up_up_id)
VALUES ((SELECT max(u_id)
         FROM test_db_data.user), (SELECT max(up_id)
                                   FROM test_db_data.user_profile));

-- admin
INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('admin', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 3);
INSERT INTO test_db_data.user_profile (up_country_id, up_first_name, up_last_name, up_city, up_birth_date)
VALUES (2, '', '', 'Kiev', DATE '1990-01-01');
INSERT INTO test_db_data.user_2_user_profile (u2up_u_id, u2up_up_id)
VALUES ((SELECT max(u_id)
         FROM test_db_data.user), (SELECT max(up_id)
                                   FROM test_db_data.user_profile));

-- admin2
INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('admin2', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 2);
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 3);
INSERT INTO test_db_data.user_profile (up_country_id, up_first_name, up_last_name, up_city, up_birth_date)
VALUES (2, 'FirstName4', 'LastName4', 'Kiev', DATE '1990-01-01');
INSERT INTO test_db_data.user_2_user_profile (u2up_u_id, u2up_up_id)
VALUES ((SELECT max(u_id)
         FROM test_db_data.user), (SELECT max(up_id)
                                   FROM test_db_data.user_profile));

--rollback DELETE FROM test_db_data.user_2_user_profile WHERE u2up_u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'admin2');
--rollback DELETE FROM test_db_data.user_2_role WHERE u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'admin2');
--rollback DELETE FROM test_db_data.user WHERE u_username = 'admin2';

--rollback DELETE FROM test_db_data.user_2_user_profile WHERE u2up_u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'admin');
--rollback DELETE FROM test_db_data.user_2_role WHERE u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'admin');
--rollback DELETE FROM test_db_data.user WHERE u_username = 'admin';

--rollback DELETE FROM test_db_data.user_2_user_profile WHERE u2up_u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'user');
--rollback DELETE FROM test_db_data.user_2_role WHERE u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'user');
--rollback DELETE FROM test_db_data.user WHERE u_username = 'user';

--rollback DELETE FROM test_db_data.user_2_user_profile WHERE u2up_u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'guest');
--rollback DELETE FROM test_db_data.user_2_role WHERE u_id IN (SELECT u_id FROM test_db_data.user WHERE u_username = 'guest');
--rollback DELETE FROM test_db_data.user WHERE u_username = 'guest';