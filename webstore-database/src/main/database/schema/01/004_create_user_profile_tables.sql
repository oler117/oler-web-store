--liquibase formatted sql

--changeset oles:create_user_profile_tables

DROP TABLE IF EXISTS test_db_data.user_2_user_profile;
DROP TABLE IF EXISTS test_db_data.user_profile;

CREATE TABLE test_db_data.user_profile (
  up_id         SERIAL PRIMARY KEY,
  up_country_id INT REFERENCES test_db_data.country (c_id),
  up_first_name VARCHAR(50) NOT NULL,
  up_last_name  VARCHAR(50) NOT NULL,
  up_city       VARCHAR(50),
  up_birth_date DATE
);

CREATE TABLE test_db_data.user_2_user_profile (
  u2up_u_id  INT UNIQUE REFERENCES test_db_data.user (u_id),
  u2up_up_id INT UNIQUE REFERENCES test_db_data.user_profile (up_id) ON DELETE CASCADE
);

--rollback DROP TABLE test_db_data.user_2_user_profile;
--rollback DROP TABLE test_db_data.user_profile;