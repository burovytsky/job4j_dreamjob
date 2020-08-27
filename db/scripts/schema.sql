CREATE TABLE IF NOT EXISTS post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   address TEXT,
   candidate_position TEXT,
   birthday TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dream_user (
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   user_password TEXT
);