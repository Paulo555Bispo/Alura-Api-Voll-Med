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

//    @Autowired  //Injeção de dependencia
//    private AgendaDeConsultas repository;

    @Autowired   /*Injeção de dependencia*/
    private AgendaDeConsultas agenda;

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>>listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = ResponseEntity.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);

    }

/*    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = ResponseEntity.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);*/

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

/*
    @GetMapping
    public Page<DadosListagemConsulta> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemConsulta::new);
        *//* Você precisará passar paramentros na URL, indicando quantos registros irá colocar em cada página,
        qual a ordem que deseja apresentar a listagem e etc.
        Você poderá tambem, passar estar informações dentro do CONTROLER, através de uma anotação
        @PageableDefault(size = 10, sort = {"nome"})
        Resumindo: Você poderá passar os parametros na API, mas prevalecerá o que for passado na URL. *//*
    }
*/

}
