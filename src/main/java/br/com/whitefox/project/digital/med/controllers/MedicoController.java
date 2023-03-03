package br.com.whitefox.project.digital.med.controllers;

import br.com.whitefox.project.digital.med.medico.DadosCadastroMedico;
import br.com.whitefox.project.digital.med.medico.DadosListagemMedico;
import br.com.whitefox.project.digital.med.medico.Medico;
import br.com.whitefox.project.digital.med.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Cadastra um novo médico passando informações pelo corpo
     *
     * @param dados
     */
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    /**
     * Traz apenas as informações que quero do DTO/record e não todas que o médico possui
     * Assim eu passo o DTO em List<>. a linha do return converte para uma lista o DTO.
     * Adicionamos o Pageable no lugar de List //padrão 20 por pagina pelo Spring
     * @return Lista de médicos
     */
    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
    }
}
