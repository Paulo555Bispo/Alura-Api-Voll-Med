package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/*Renomeando, para não haver conflito com Classe de mesmo nome*/
@Component("ValidadorHorarioAntecedenciaAgendamento")  /*Componente genérico, para ser usado na inicialização do projeto.*/
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30 ) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 (trinta) minutos.");
        }
    }
}
