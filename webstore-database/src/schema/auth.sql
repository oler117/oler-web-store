CREATE TABLE test_db_data.user (
  u_id       SERIAL PRIMARY KEY,
  u_password CHARACTER VARYING(255),
  u_username CHARACTER VARYING(255)
);

CREATE TABLE test_db_data.role (
  r_id       SERIAL PRIMARY KEY,
  r_rolename CHARACTER VARYING(255)
);

CREATE TABLE test_db_data.user_2_role (
  u_id INTEGER NOT NULL,
  r_id INTEGER NOT NULL,
  FOREIGN KEY (u_id) REFERENCES test_db_data.user (u_id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  FOREIGN KEY (r_id) REFERENCES test_db_data.role (r_id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);