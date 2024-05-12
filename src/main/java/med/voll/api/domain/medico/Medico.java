package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter  /*Anotações do Lombok*/
@NoArgsConstructor  /*Gerar construtor default*/
@AllArgsConstructor  /*Para ter um construtor que recebe todos os campos.*/
@EqualsAndHashCode(of = "id")  /*Para gerar HashCode em cima do ID.*/
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String nome;
    @Email @NotBlank private String email;
    @NotBlank private String telefone;
    @NotBlank private String crm;
    @Enumerated(EnumType.STRING) private Especialidade especialidade;
    @Embedded private Endereco endereco;  /*Está em uma classe separada, porem na mesma tabela.*/
    private boolean ativo;


    /*Construtor criado para pegar os dados (DTO-JSON)*/
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    /*Construtor criado para pegar os dados (DTO-JSON)*/
    public void atualizarInformacoes(DadosAtualizarMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
