package br.com.whitefox.project.digital.med.domain.medico;

import br.com.whitefox.project.digital.med.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
