CREATE TABLE consultas(
    id bigserial NOT NULL,
    medico_id bigserial NOT NULL,
    paciente_id bigserial NOT NULL,
    data timestamp NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_consultas_medico_id FOREIGN KEY(medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY(paciente_id) REFERENCES pacientes(id)
);