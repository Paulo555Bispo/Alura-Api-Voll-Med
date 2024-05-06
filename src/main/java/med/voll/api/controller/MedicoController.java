package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.DadosAtualizarMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController  /*Essa anotação, serve para que o Spring carregue essa classe na inicialização do projeto.*/
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired  /*Injeção de dependencia*/
    private MedicoRepository repository;

    @PostMapping
    @Transactional  /*Só é necessário informar, quando realizar uma transação com o banco de dados.*/
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

        /* O CONTROLER recebe um objeto DTO. Então se faz necessário a conversão de um DTO para
        *  um objeto/entidade JPA. Para facilitar e evitar ter que informar todos os campos da classe Médico,
        *  devemos criar um construtor na classe Medico. */
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

        /* Você precisará passar paramentros na URL, indicando quantos registros irá colocar em cada página,
        qual a ordem que deseja apresentar a listagem, etc.
        Você poderá tambem, passar estar informações dentro do CONTROLER, através de uma anotação
        @PageableDefault(size = 10, sort = {"nome"})
        Resumindo: Você poderá passar os parametros na API, mas prevalecerá o que for passado na URL. */
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizar(@RequestBody @NotNull @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id()); /*O Repository é usado para acessar o Banco de Dados*/
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }

}


