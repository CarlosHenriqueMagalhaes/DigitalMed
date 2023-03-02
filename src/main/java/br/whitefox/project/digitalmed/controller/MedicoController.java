package br.whitefox.project.digitalmed.controller;

import br.whitefox.project.digitalmed.medico.DadosCadastroMedico;
import br.whitefox.project.digitalmed.medico.DadosListagemMedico;
import br.whitefox.project.digitalmed.medico.Medico;
import br.whitefox.project.digitalmed.medico.MedicoRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Cadastra um novo médico passando informações pelo corpo
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
     * @return Lista de médicos
     */
    @GetMapping
    public List<DadosListagemMedico> listar(){
        return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
