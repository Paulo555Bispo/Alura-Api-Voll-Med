package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;


public record DadosDetalhamentoConsulta(Long id,
                                        Long idPaciente,
                                        Long idMedico,
                                        LocalDateTime data,
                                        Especialidade especialidade,
                                        MotivoCancelamento motivoCancelamento
                                        ) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(   consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getMedico().getId(),
                consulta.getData(),
                consulta.getEspecialidade(),
                consulta.getMotivoCancelamento()
        );
    }

}