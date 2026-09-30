CREATE
DATABASE proyecto_spring;

CREATE TABLE tb_usuarios
(
    cedula   VARCHAR(12) PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    correo   VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol      VARCHAR(20)  NOT NULL
);

CREATE TABLE tb_calificaciones
(
    cid               INT PRIMARY KEY,
    estudiante        VARCHAR(100) NOT NULL,
    docente           VARCHAR(100) NOT NULL,
    asignatura        VARCHAR(100) NOT NULL,
    carrera           VARCHAR(100) NOT NULL,
    universidad       VARCHAR(100) NOT NULL,
    periodo           VARCHAR(20) NOT NULL,
    actividadEvaluada VARCHAR(100) NOT NULL,
    nota              DECIMAL(2, 1),
    fecha             TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


