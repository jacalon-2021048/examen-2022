DROP DATABASE IF EXISTS db_control_materia_in5bm;
CREATE DATABASE db_control_materia_in5bm;

USE db_control_materia_in5bm;

/**
 *
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 26/09/2022
 * @time 11:41:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */

DROP TABLE IF EXISTS materias;

CREATE TABLE IF NOT EXISTS materias(
	id_materia INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre_materia VARCHAR(45) NOT NULL,
    ciclo_escolar YEAR NULL,
    horario_inicio TIME NULL,
    horario_final TIME NULL,
    catedratico VARCHAR(45) NOT NULL,
    salon VARCHAR(45) NOT NULL,
    cupo_maximo INT NULL,
    cupo_minimo INT NULL,
    nota INT NOT NULL
);

INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Ciencias",'2022','07:00','12:00',"Pedro Hernandez","E40",30,10,60);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Matematica",'2022','07:00','10:00',"Pedro Pascal","A40",40,10,70);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Quimica",'2022','07:00','08:00',"Guillermo Sosa","D50",37,10,65);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Taller",'2022','07:00','09:30',"Rodrigo Pelaes","G50",67,10,60);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Lengua",'2022','07:00','07:40',"Federico Gramajo","F62",56,10,65);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Sociales",'2022','09:00','10:30',"Ivan Gonzales","C62",50,10,70);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Tecnologia",'2022','07:00','09:00',"Guillermo Andrade","C97",89,10,75);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("TICS",'2022','07:00','10:00',"Michael Hernandez","B06",64,10,60);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Estadistica",'2022','07:00','12:00',"Andre Sosa","B04",86,10,65);
INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota)
VALUES("Etica",'2022','07:00','09:00',"Marcos Fernandez","V94",45,10,60);