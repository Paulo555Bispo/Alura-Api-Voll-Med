package med.voll.api.domain.medico;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record DadosListagemMedico(Long id,
                                  String nome,
                                  String email,
                                  String telefone,
                                  String crm,
                                  Especialidade especialidade) {

    /*Construtor*/
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
