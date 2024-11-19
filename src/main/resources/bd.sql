drop database biblioteca;

CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    anio INT NOT NULL
);

drop table libros;

INSERT INTO libros (titulo, autor, anio) VALUES
('Java Programming', 'John Doe', 2023),
('Effective Java', 'Joshua Bloch', 2018),
('Java: The Complete Reference', 'Herbert Schildt', 2022),
('Clean Code', 'Robert C. Martin', 2008),
('Head First Java', 'Kathy Sierra', 2005),
('Java Concurrency in Practice', 'Brian Goetz', 2006),
('Spring in Action', 'Craig Walls', 2018),
('Java Performance', 'Charlie Hunt', 2011),
('Design Patterns', 'Erich Gamma', 1994),
('Refactoring', 'Martin Fowler', 1999),
('Test Driven Development', 'Kent Beck', 2002);
