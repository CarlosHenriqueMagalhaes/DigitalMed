package br.com.whitefox.project.digital.med.infra.security;

import br.com.whitefox.project.digital.med.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${digitalmed.security.token.secret}")
    private String secret;

    /**
     * método da lib jwt.io copiada do tutorial https://github.com/auth0/java-jwt
     * foram feitas modificações para se adequar ao projeto
     * Importante:
     * É recomendado configurar uma data de expiração para os Tokens
     *
     * @return
     */
    public String gerarToken(Usuario usuario) {
        try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API digitalmed")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    /**
     * Método que configura a data de expiração
     * @return
     */
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
