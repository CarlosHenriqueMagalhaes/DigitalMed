package br.com.whitefox.project.digital.med.controllers;

import br.com.whitefox.project.digital.med.domain.usuario.DadosAutenticacao;
import br.com.whitefox.project.digital.med.domain.usuario.Usuario;
import br.com.whitefox.project.digital.med.infra.security.DadosTokenJWT;
import br.com.whitefox.project.digital.med.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * A linha:
     * var authentication = authenticationManager.authenticate(token);
     * //devolve um objeto que representa um usuario autenticado no sistema
     * UsernamePasswordAuthenticationToken:
     * Método de autenticação do proprio Spring
     * @param dadosAutenticacao
     * @return
     */
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(), dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT =  tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
