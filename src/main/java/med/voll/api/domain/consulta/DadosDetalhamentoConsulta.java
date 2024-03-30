package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id,
                                        Long idMedico,
                                        Long idPaciente,
                                        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                        LocalDateTime data,
                                        String motivoCancelamento
                                        ) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(),
                consulta.getPaciente().getId(), consulta.getData(), consulta.getMotivoCancelamento());
    }

}