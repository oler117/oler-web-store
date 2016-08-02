--liquibase formatted sql

--changeset oles:create_country_table

DROP TABLE IF EXISTS test_db_data.country;

CREATE TABLE test_db_data.country (
  c_id       SERIAL PRIMARY KEY,
  c_iso_code VARCHAR(2)   NOT NULL,
  c_name     VARCHAR(100) NOT NULL
);

--rollback DROP TABLE test_db_data.country;