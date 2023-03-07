package br.com.whitefox.project.digital.med.domain.paciente;

import br.com.whitefox.project.digital.med.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}