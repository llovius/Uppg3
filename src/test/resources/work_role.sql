create table if not exists work_role (
    role_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
     title VARCHAR (50) NOT NULL,
   description VARCHAR (50) NOT NULL,
   salary double NOT NULL,
   creation_date DATE NOT NULL,
   PRIMARY KEY(role_id)
);
