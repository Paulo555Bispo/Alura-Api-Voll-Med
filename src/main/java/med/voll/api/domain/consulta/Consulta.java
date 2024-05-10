package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "medico_id") private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "paciente_id") private Paciente paciente;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING) private Especialidade especialidade;
    @Column(name = "motivo_cancelamento") @Enumerated(EnumType.STRING) private MotivoCancelamento motivoCancelamento;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime data, Especialidade especialidade, MotivoCancelamento motivoCancelamento) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.especialidade = especialidade;
        this.motivoCancelamento = motivoCancelamento;
    }

    public void cancelarConsultaMotivo(DadosCancelamentoConsulta dados) {
        if (dados.motivoCancelamento() != null) {
            this.motivoCancelamento = dados.motivoCancelamento();
        }
    }
}
