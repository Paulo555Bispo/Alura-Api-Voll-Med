package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  /*Componente genérico, para ser usado na inicialização do projeto.*/
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired /*Usado para quem está usando o Repository*/
    private MedicoRepository repository;  /*Usado para acessar o banco de dados*/

    public void  validar(DadosAgendamentoConsulta dados) {
        /*Escolha do médico opcional*/
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }
}
