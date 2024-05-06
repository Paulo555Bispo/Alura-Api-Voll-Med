package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosCancelamentoConsulta(
        Long idConsulta,
/*        @NotNull
        Long idPaciente,
        @NotNull
        Long idMedico,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Especialidade especialidade,*/
        MotivoCancelamento motivoCancelamento) {

/*        public DadosCancelamentoConsulta(Consulta consulta) {
                this(consulta.getId(),
                        consulta.getIdPaciente(),
                        consulta.getIdMedico(),
                        consulta.getData(),
                        consulta.getEspecialidade(),
                        consulta.getMotivoCancelamento()
                );
        }*/
}
