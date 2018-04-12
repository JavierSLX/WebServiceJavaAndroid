DROP DATABASE IF EXISTS escuela;
CREATE DATABASE escuela;
USE escuela;

CREATE TABLE alumno
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE materia
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(30) UNIQUE NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE registro
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    alumno_id INT NOT NULL,
    materia_id INT NOT NULL,
    FOREIGN KEY (alumno_id) REFERENCES alumno(id),
    FOREIGN KEY (materia_id) REFERENCES materia(id)
);

CREATE TABLE boleta
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    calificacion DOUBLE NOT NULL CHECK (calificacion > 0 AND calificacion <= 10),
    registro_id INT NOT NULL,
    FOREIGN KEY (registro_id) REFERENCES registro(id)
);

INSERT INTO alumno (nombre, apellido, estado) VALUES ('Javier', 'Serrano', true);
INSERT INTO alumno (nombre, apellido, estado) VALUES ('Juan', 'Rulfo', true);
INSERT INTO alumno (nombre, apellido, estado) VALUES ('Albert', 'Einstein', true);

INSERT INTO materia (nombre, estado) VALUES ('Matemáticas', true);
INSERT INTO materia (nombre, estado) VALUES ('Español', true);
INSERT INTO materia (nombre, estado) VALUES ('Programación', true);
INSERT INTO materia (nombre, estado) VALUES ('Física', true);

INSERT INTO registro (alumno_id, materia_id) VALUES (1, 1);
INSERT INTO registro (alumno_id, materia_id) VALUES (1, 3);
INSERT INTO registro (alumno_id, materia_id) VALUES (1, 4);
INSERT INTO registro (alumno_id, materia_id) VALUES (2, 2);
INSERT INTO registro (alumno_id, materia_id) VALUES (3, 1);
INSERT INTO registro (alumno_id, materia_id) VALUES (3, 4);

INSERT INTO boleta (calificacion, registro_id) VALUES (9, 3);
INSERT INTO boleta (calificacion, registro_id) VALUES (7, 6);