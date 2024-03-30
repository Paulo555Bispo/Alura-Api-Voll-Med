package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id, LocalDateTime Data, Medico medico,
                                    Paciente paciente, String motivoCancelamento) {

    /*Construtor*/
    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getData(), consulta.getMedico(), consulta.getPaciente(),
                consulta.getMotivoCancelamento());
    }
}
