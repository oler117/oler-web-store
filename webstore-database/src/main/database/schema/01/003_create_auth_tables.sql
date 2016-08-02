--liquibase formatted sql

--changeset oles:create_auth_tables

DROP TABLE IF EXISTS test_db_data.user_temp_token;
DROP TABLE IF EXISTS test_db_data.user_2_role;
DROP TABLE IF EXISTS test_db_data.role;
DROP TABLE IF EXISTS test_db_data.user;

CREATE TABLE test_db_data.user (
  u_id        SERIAL PRIMARY KEY,
  u_username  VARCHAR(50),
  u_password  VARCHAR(255),
  u_is_active BOOL DEFAULT FALSE
);

CREATE TABLE test_db_data.role (
  r_id       SERIAL PRIMARY KEY,
  r_rolename VARCHAR(50) UNIQUE
);

CREATE TABLE test_db_data.user_2_role (
  u_id INTEGER NOT NULL,
  r_id INTEGER NOT NULL,
  FOREIGN KEY (u_id) REFERENCES test_db_data.user (u_id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (r_id) REFERENCES test_db_data.role (r_id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE test_db_data.user_temp_token (
  utt_id         SERIAL PRIMARY KEY,
  utt_user_id    INT REFERENCES test_db_data.user (u_id),
  utt_token_type TEXT
    CHECK (utt_token_type IN ('TOKEN_REGISTRATION_CONFIRM', 'TOKEN_FORGOT_PASSWORD')),
  utt_token      VARCHAR(255) UNIQUE NOT NULL
);

--rollback DROP TABLE test_db_data.user_temp_token;
--rollback DROP TABLE test_db_data.user_2_role;
--rollback DROP TABLE test_db_data.role;
--rollback DROP TABLE test_db_data.user;