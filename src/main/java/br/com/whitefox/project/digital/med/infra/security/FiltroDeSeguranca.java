package br.com.whitefox.project.digital.med.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Essa classe vai garantir que todos os métodos sejam bloqueados e acessados apenas
 * com o token gerado no login
 */
@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {

    /**
     * filterChain.doFilter(request,response): necessário para chamar os próximos
     * filtros na aplicação
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var tokeJWT = recuperarToken(request);
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null){
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }
        return  authorizationHeader.replace("Bearer","");
    }
}
