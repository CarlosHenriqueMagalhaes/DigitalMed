package br.com.whitefox.project.digital.med.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank(message = "Dados do endereço são obrigatórios")
        String logradouro,
        @NotBlank(message = "Dados do endereço são obrigatórios")
        String bairro,
        @NotBlank(message = "Dados do endereço são obrigatórios")
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank(message = "Dados do endereço são obrigatórios")
        String cidade,
        @NotBlank(message = "Dados do endereço são obrigatórios")
        String uf,
        @NotBlank(message = "Dados do endereço são obrigatórios")
        String numero,
        String complemento) {
}
