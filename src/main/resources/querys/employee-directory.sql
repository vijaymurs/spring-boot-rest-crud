CREATE TABLE IF NOT EXISTS spring_boot.employee (
   id serial PRIMARY KEY NOT NULL,
   first_name varchar (50) NOT NULL,
   last_name varchar (50) NOT NULL,
   email varchar (255) NOT NULL UNIQUE,
   is_deleted BOOLEAN DEFAULT FALSE
);

INSERT INTO spring_boot.employee (first_name,last_name,email) VALUES 
	('vijay','murugan','vijay@gamil.com'),
	('jay','vijay','jay@gmail.com');