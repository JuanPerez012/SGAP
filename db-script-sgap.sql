CREATE TABLE amenazas (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(100),
    descripcion TEXT,
    severidad VARCHAR(50)
);

CREATE TABLE archivos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    tipo_archivo VARCHAR(100),
    hash TEXT,
    tamano BIGINT,
    ruta TEXT,
    nivel_riesgo VARCHAR(50),
    estado VARCHAR(50),
    amenaza_id INT REFERENCES amenazas(id)
);

CREATE TABLE eventos (
    id SERIAL PRIMARY KEY,
    tipo_evento VARCHAR(100),
    descripcion TEXT,
    fecha TIMESTAMP
);