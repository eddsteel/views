DROP TABLE IF EXISTS view CASCADE;
DROP TABLE IF EXISTS content CASCADE;
DROP TYPE IF EXISTS medium;

CREATE TYPE medium AS ENUM('film', 'book');

CREATE TABLE content (
  id SERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  creator VARCHAR NOT NULL,
  year SMALLINT,
  medium medium,

  CONSTRAINT uq_name_year UNIQUE (name, year)
);

CREATE TABLE view (
  id SERIAL PRIMARY KEY,
  content_id SERIAL REFERENCES content(id),
  opinion VARCHAR
);
