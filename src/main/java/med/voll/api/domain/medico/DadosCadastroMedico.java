package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

/*A classe record (padrão DTO), é uma classe imutável do Java*/
/*Colocando as anotações de validações dos campos*/
public record DadosCadastroMedico(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,  /*EXPRESSÃO REGULAR, informando que receberá de quatro a seis dígitos*/
        @NotNull Especialidade especialidade,
        @NotNull @Valid DadosEndereco endereco) {
}
