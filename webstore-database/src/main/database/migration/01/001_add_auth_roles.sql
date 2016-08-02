--liquibase formatted sql

--changeset oles:add_auth_roles

INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_GUEST');
INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_USER');
INSERT INTO test_db_data.role (r_rolename) VALUES ('ROLE_ADMIN');

--rollback DELETE FROM test_db_data.role WHERE r_rolename = 'ROLE_ADMIN';
--rollback DELETE FROM test_db_data.role WHERE r_rolename = 'ROLE_USER';
--rollback DELETE FROM test_db_data.role WHERE r_rolename = 'ROLE_GUEST';