package br.whitefox.project.digitalmed.paciente;

import br.whitefox.project.digitalmed.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
