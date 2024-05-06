package med.voll.api.domain.paciente;

import med.voll.api.domain.paciente.Paciente;

public record DadosListagemPaciente(Long id,
                                    String nome,
                                    String email,
                                    String cpf,
                                    String telefone) {

    public DadosListagemPaciente(Paciente paciente) {
        this(   paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf(),
                paciente.getTelefone());
    }
}
