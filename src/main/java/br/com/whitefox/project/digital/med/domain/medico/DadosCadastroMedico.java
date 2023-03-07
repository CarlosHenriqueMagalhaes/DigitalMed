package br.com.whitefox.project.digital.med.domain.medico;

import br.com.whitefox.project.digital.med.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank (message = "Nome é obrigatório") //NotBlank é apenas para campos String
        String nome,
        @NotBlank (message = "Email é obrigatório")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}") //significa de 4 a 6 digitos
        String crm,
        String telefone,
        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,
        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid
        DadosEndereco endereco) {
}
