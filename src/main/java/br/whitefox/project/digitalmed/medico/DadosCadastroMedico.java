package br.whitefox.project.digitalmed.medico;

import br.whitefox.project.digitalmed.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
