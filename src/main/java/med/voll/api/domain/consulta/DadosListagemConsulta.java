package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosListagemConsulta(Long id,
                                    Long idPaciente,
                                    Long idMedico,
                                    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime Data,
                                    Especialidade especialidade,
                                    MotivoCancelamento motivoCancelamento) {

    /*Construtor*/
    public DadosListagemConsulta(Consulta consulta) {
        this(   consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getMedico().getId(),
                consulta.getData(),
                consulta.getEspecialidade(),
                consulta.getMotivoCancelamento()
        );
    }
}
