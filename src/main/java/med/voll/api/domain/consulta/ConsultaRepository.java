package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    Page<Consulta> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select c.ativo
            from Consulta c
            where
            c.id = :idConsulta
            """)

    Boolean findAtivoById(Long idConsulta);

    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idConsulta, LocalDateTime data);

    // Alterado para que o paciente possa trocar o horário da consulta
    /*boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);*/
    boolean existsByPacienteIdAndDataBetweenAndMotivoCancelamentoIsNull(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}