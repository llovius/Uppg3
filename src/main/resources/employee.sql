CREATE TABLE IF NOT EXISTS employee (
    employee_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    name VARCHAR (50) NOT NULL,
    email VARCHAR (70) NOT NULL,
    password VARCHAR (20) NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (role_id) REFERENCES work_role(role_id)
);