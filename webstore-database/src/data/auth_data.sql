INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_GUEST');
INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_USER');
INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_ADMIN');

-- TEST DATA
INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('guest', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 1);

INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('user', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 2);

INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('admin', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 3);

INSERT INTO test_db_data.user (u_username, u_password)
VALUES ('admin2', '$2a$08$MVw.jytALX1UciMx1drade.Fi5Vef/eKYkJavNjpaK9tNriX5jI9K'); -- psw = 1
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 2);
INSERT INTO test_db_data.user_2_role (u_id, r_id) VALUES ((SELECT max(u_id)
                                                           FROM test_db_data.user), 3);