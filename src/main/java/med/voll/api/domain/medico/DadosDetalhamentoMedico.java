package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.Especialidade;

public record DadosDetalhamentoMedico(
                                      Long id,
                                      String nome,
                                      String email,
                                      String crm,
                                      String telefone,
                                      Especialidade especialidade,
                                      Endereco endereco
                                      ) {
    /*Construtor*/
    public DadosDetalhamentoMedico(Medico medico) {
        this(   medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }

}
