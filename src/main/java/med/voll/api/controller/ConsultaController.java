package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.medico.DadosAtualizarMedico;
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

/*    @PutMapping()
    @jakarta.transaction.Transactional  *//*É usado quando houver uma transasão com o BD*//*
    public ResponseEntity atualizar(@RequestBody @NotNull @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id()); *//*O Repository é usado para acessar o Banco de Dados*//*
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }*/


    @PutMapping()
    @Transactional  /*É usado quando houver uma transação com o BD*/
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.id());
        consulta.cancelarConsultaMotivo(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
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
