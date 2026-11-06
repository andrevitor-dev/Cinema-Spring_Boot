CREATE TABLE Filme (
    id_filme SERIAL PRIMARY KEY,
    titulo VARCHAR NOT NULL,
    dataLancamento DATE,
    genero VARCHAR
);

CREATE TABLE Diretor (
    id_diretor SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE,
    email VARCHAR(100),
    telefone VARCHAR(20),
    id_nacionalidade INTEGER REFERENCES Nacionalidade(id_nacionalidade)
);

CREATE TABLE Nacionalidade (
    id_nacionalidade SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

ALTER TABLE Diretor 
ADD id_nacionalidade INTEGER;

ALTER TABLE Diretor 
ADD FOREIGN KEY (id_nacionalidade) REFERENCES Nacionalidade(id_nacionalidade);

INSERT INTO Nacionalidade (nome) VALUES 
('Americana'),
('Britânica');

INSERT INTO Diretor (nome, data_nascimento, email, telefone, id_nacionalidade) VALUES
('Christopher Nolan', '1970-07-30', 'nolan@cinema.com', 99991111, 2),
('Lana Wachowski', '1965-06-21', 'lana@cinema.com', 99992222, 1),
('Steven Spielberg', '1946-12-18', 'steven@cinema.com', 99993333, 3);

INSERT INTO Filme (titulo, dataLancamento, genero) VALUES
('Inception', '2010-07-16', 'Ficção Científica'),
('The Matrix', '1999-03-31', 'Ficção Científica'),
('Jurassic Park', '1993-06-11', 'Aventura');

SELECT * FROM Nacionalidade;

SELECT * FROM Diretor;

SELECT * FROM Filme;

