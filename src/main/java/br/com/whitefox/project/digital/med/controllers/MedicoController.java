package br.com.whitefox.project.digital.med.controllers;

import br.com.whitefox.project.digital.med.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
     * Funciona sem o @PageableDefault, porém com ele eu "seto" um padrão
     * @return Lista de médicos
     */
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    /**
     * Esse método não deleta do banco de dados, ele deixa inativo o
     * médico isso é chamado de "Exclusão lógica"
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void exluir (@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
      medico.excluir();
    }
}
