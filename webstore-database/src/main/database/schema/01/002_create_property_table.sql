--liquibase formatted sql

--changeset oles:create_property_table

DROP TABLE IF EXISTS test_db_data.property;

CREATE TABLE test_db_data.property (
  p_id    SERIAL PRIMARY KEY,
  p_key   VARCHAR(100) UNIQUE NOT NULL,
  p_value TEXT
);

--rollback DROP TABLE test_db_data.property;