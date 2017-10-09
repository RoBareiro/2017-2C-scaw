/* Roles */
INSERT INTO Roles
VALUES(1, 'Administrador');
INSERT INTO Roles
VALUES(2, 'Docente');
INSERT INTO Roles
VALUES(3, 'Alumno');

/* Estados Usuarios */
INSERT INTO EstadosUsuarios
VALUES(1, 'Pendiente Habilitación');
INSERT INTO EstadosUsuarios
VALUES(2, 'Habilitado');
INSERT INTO EstadosUsuarios
VALUES(3, 'Habilitación Rechazada');
INSERT INTO EstadosUsuarios
VALUES(4, 'Eliminado');

/* Usuarios */
INSERT INTO Usuarios
VALUES(1, 'cgioia@unlam.edu.ar', '1234', 'Gioia', 'Cintia', 2);
INSERT INTO Usuarios
VALUES(2, 'wureta@unlam.edu.ar', '1234', 'Ureta', 'Walter', 2);
INSERT INTO Usuarios
VALUES(3, 'aborgeat@unlam.edu.ar', '1234', 'Borgeat', 'Andres', 2);
INSERT INTO Usuarios
VALUES(4, 'jmonteagudo@unlam.edu.ar', '1234', 'Monteagudo', 'Juan', 2);

INSERT INTO Usuarios
VALUES(5, 'bareiro@unlam.edu.ar', 'rocio*07', 'Bareiro', 'Rocio', 2);
INSERT INTO Usuarios
VALUES(6, 'castaner@unlam.edu.ar', 'rocio*09', 'Castaner', 'Rocio', 2);
INSERT INTO Usuarios
VALUES(7, 'dortona@unlam.edu.ar', 'toto_velez', 'Dortona', 'Tomas', 2);
INSERT INTO Usuarios
VALUES(8, 'martinez@unlam.edu.ar', 'mati_mati', 'Martinez', 'Matias Sebastian', 2);

/* RolesUsuarios */
INSERT INTO RolesUsuarios
VALUES(1, 1);
INSERT INTO RolesUsuarios
VALUES(1, 2);
INSERT INTO RolesUsuarios
VALUES(2, 1);
INSERT INTO RolesUsuarios
VALUES(2, 2);
INSERT INTO RolesUsuarios
VALUES(3, 1);
INSERT INTO RolesUsuarios
VALUES(3, 2);
INSERT INTO RolesUsuarios
VALUES(3, 3);
INSERT INTO RolesUsuarios
VALUES(4, 1);
INSERT INTO RolesUsuarios
VALUES(4, 2);
INSERT INTO RolesUsuarios
VALUES(4, 3);

INSERT INTO RolesUsuarios
VALUES(5, 1);
INSERT INTO RolesUsuarios
VALUES(6, 1);
INSERT INTO RolesUsuarios
VALUES(7, 1);
INSERT INTO RolesUsuarios
VALUES(9, 1);

/* EstadosMaterias */
INSERT INTO EstadosMaterias
VALUES (1, 'Materia Habilitada');
INSERT INTO EstadosMaterias
VALUES (2, 'Materia Eliminada');

/* Materias */
INSERT INTO Materias
VALUES(1, 'Filosofia', 2, 1);
INSERT INTO Materias
VALUES(2, 'Hablemos sin saber', 3, 1);

/* EstadosExamenes */
INSERT INTO EstadosExamenes
VALUES(1, 'Pendiente');
INSERT INTO EstadosExamenes
VALUES(2, 'Examen en curso');
INSERT INTO EstadosExamenes
VALUES(3, 'Examen finalizado');

/* Examenes */
INSERT INTO Examenes
VALUES(1, 'primer parcial', 1, 1);

/* Preguntas */
INSERT INTO Preguntas
VALUES(1, 1, 'Ser o no ser?');
INSERT INTO Preguntas
VALUES(2, 1, 'Primero fue el huevo o la gallina?'); 
INSERT INTO Preguntas
VALUES(3, 1, 'Porque la gallina cruzo el camino?'); 
INSERT INTO Preguntas
VALUES(4, 1, 'KNOC KNOC...Quien es?');
INSERT INTO Preguntas
VALUES(5, 1, 'Por qué eres así?');

/* TiposRespuestas */
INSERT INTO TiposRespuestas
VALUES(1, 'Repuesta Correcta');
INSERT INTO TiposRespuestas
VALUES(2, 'Repuesta Incorrecta');

/* Respuestas */ 
/* Pregunta 1: 'Ser o no ser?' */
INSERT INTO Respuestas
VALUES(1, 1, 'ser', 1);
INSERT INTO Respuestas
VALUES(2, 1, 'no ser', 2);
/* Pregunta 2: 'Primero fue el huevo o la gallina?' */
INSERT INTO Respuestas
VALUES(3, 2, 'gallina', 2);
INSERT INTO Respuestas
VALUES(4, 2, 'ninguno', 2);
INSERT INTO Respuestas
VALUES(5, 2, 'ambos', 2);
INSERT INTO Respuestas
VALUES(6, 2, 'huevo', 1);
/* Pregunta 3: 'Porque la gallina cruzo el camino?' */
INSERT INTO Respuestas
VALUES(7, 3, 'porque si', 2);
INSERT INTO Respuestas
VALUES(8, 3, 'porque no', 2);
INSERT INTO Respuestas
VALUES(9, 3, 'para llegar al otro lado', 1);
INSERT INTO Respuestas
VALUES(10, 3, 'no se', 2);
/* Pregunta 4: 'KNOC KNOC...Quien es?' */
INSERT INTO Respuestas
VALUES(11, 4, 'soy yo', 1);
INSERT INTO Respuestas
VALUES(12, 4, 'no se', 2);
/* Pregunta 5: 'Por qué eres así?' */
INSERT INTO Respuestas
VALUES(13, 5, 'no se', 2);
INSERT INTO Respuestas
VALUES(14, 5, 'porque si', 1);