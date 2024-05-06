package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }


/*
    @PostMapping
    @Transactional  *//*Só é necessário informar, quando realizar uma transação com o banco de dados.*//*
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados, UriComponentsBuilder uriComponentsBuilder) {
        var consulta = new Consulta(dados);
        repository.save(consulta);

        var uri = uriComponentsBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoConsulta(consulta));
    }*/

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

   @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemConsulta::new);
        //var page = repository.todasConsultas(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }
/*
    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = ResponseEntity.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);*/


    /*Você precisará passar parâmetros na URL, indicando quantos registros irá colocar em cada página,
    qual a ordem que deseja apresentar a listagem e etc.
    Você poderá também, passar estar informações dentro do CONTROLLER, através de uma anotação
    @PageableDefault(size = 10, sort = {"nome"})
    Resumindo: Você poderá passar os parâmetros na API, mas prevalecerá o que for passado na URL.*/


}
