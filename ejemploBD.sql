create database ejemplo;
use ejemplo;

-- Creación de la tabla persona
CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero ENUM('M', 'F', 'Otro') NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    ocupacion VARCHAR(100),
    estado_civil ENUM('Soltero', 'Casado', 'Divorciado', 'Viudo'),
    nacionalidad VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Creación de la tabla direccion
CREATE TABLE direccion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL,
    tipo_direccion ENUM('Casa', 'Trabajo', 'Otro') NOT NULL,
    direccion TEXT NOT NULL,
    activado BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE
);

-- Ejemplo de inserción de datos en la tabla persona
INSERT INTO persona (nombre, apellido, fecha_nacimiento, genero, email, telefono, ocupacion, estado_civil, nacionalidad)
VALUES
('Juan', 'Pérez', '1985-04-23', 'M', 'juan.perez@example.com', '555-1234', 'Ingeniero', 'Casado', 'Peruano');

-- Ejemplo de inserción de datos en la tabla direccion
INSERT INTO direccion (persona_id, tipo_direccion, direccion, activado)
VALUES
(1, 'Casa', 'Av. Principal 123, Lima', TRUE),
(1, 'Trabajo', 'Calle Secundaria 456, Lima', TRUE);

select * from persona;
select * from direccion;

SELECT 
    p.id AS persona_id,
    p.nombre,
    p.apellido,
    d.tipo_direccion,
    d.direccion,
    d.activado
FROM 
    persona p
JOIN 
    direccion d ON p.id = d.persona_id;
    
    
-- Creación de la tabla usuario
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Creación de la tabla de registro (log)
CREATE TABLE registro_acceso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    persona_id INT NOT NULL,
    fecha_acceso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);


-- Inserción de un nuevo usuario
INSERT INTO usuario (nombre, email, password)
VALUES
('maria', 'cmasd@example.com', '123');
-- Selección de todos los usuarios
SELECT * FROM usuario;



-- Inserción de un registro de acceso
INSERT INTO registro_acceso (usuario_id, persona_id)
VALUES
(1, 1); -- Supongamos que el usuario con id 1 accede a la persona con id 1

-- Selección de datos de la tabla de registro junto con información de persona y usuario
SELECT 
    ra.id AS registro_id,
    ra.fecha_acceso,
    u.id AS usuario_id,
    u.nombre AS usuario_nombre,
    p.id AS persona_id,
    p.nombre AS persona_nombre,
    p.apellido AS persona_apellido
FROM 
    registro_acceso ra
JOIN 
    usuario u ON ra.usuario_id = u.id
JOIN 
    persona p ON ra.persona_id = p.id;



