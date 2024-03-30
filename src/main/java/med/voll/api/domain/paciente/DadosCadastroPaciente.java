package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{10,12}")   /*EXPRESSÃO REGULAR*/
        String telefone,
        @NotBlank @Pattern(regexp = "\\d{11}")  /*EXPRESSÃO REGULAR*/
        String cpf,
        @NotNull @Valid DadosEndereco endereco) {
}
