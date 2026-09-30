CREATE
DATABASE proyecto_spring;

CREATE TABLE tb_usuarios
(
    cedula   VARCHAR(12) PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    correo   VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol      VARCHAR(20)     NOT NULL
);

