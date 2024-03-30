package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.paciente.DadosAtualizarPaciente;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired   /*Injeção de dependencia*/
    private AgendaDeConsultas agenda;

    @Autowired  /*Injeção de dependencia*/
    private AgendaDeConsultas repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }


    @PutMapping()
    @jakarta.transaction.Transactional
    public void cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta()); /*O Repository é usado para acessar o Banco de Dados*/
        consulta.cancelar(dados.motivoCancelamento());

    }


    @GetMapping
    public Page<DadosListagemConsulta> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        /* Você precisará passar paramentros na URL, indicando quantos registros irá colocar em cada página,
        qual a ordem que deseja apresentar a listagem e etc.
        Você poderá tambem, passar estar informações dentro do CONTROLER, através de uma anotação
        @PageableDefault(size = 10, sort = {"nome"})
        Resumindo: Você poderá passar os parametros na API, mas prevalecerá o que for passado na URL. */
    }

}
