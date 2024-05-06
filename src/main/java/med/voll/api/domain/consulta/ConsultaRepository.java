package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

    Page<Consulta> findAll(Pageable paginacao);
    //Page<Consulta> findAllByAtivoTrue(Pageable paginacao);

/*    @Query("""
            select c.*, m.especialidade
            from Consulta c
            left join medicos m on m.id = c.id
            """)
    Page<Consulta> todasConsultas(Pageable paginacao);*/

    /*
                where
                c.id = :idConsulta
                --and
            --    c.motivo_cancelamento is NULL*/
    //Boolean escolherConsultaAtiva(Long idConsulta);


//    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data, MotivoCancelamento motivoCancelamento);
    boolean existsByMedicoIdAndData(Long idConsulta, LocalDateTime data);

    // Alterado para que o paciente possa trocar o horário da consulta
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
 //   boolean existsByPacienteIdAndDataBetweenAndMotivoCancelamentoIsNull(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario, MotivoCancelamento motivoCancelamento);
}