package br.com.whitefox.project.digital.med.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {
    /**
     * CSRF Cross-Site Request Forgery:
     * .csrf desabilita o forge pois o proprio token a ser usado proteje do ataque acima;
     * sessionManagement:
     * mostra como vai ser o gerenciamento da Seção
     * sessionCreationPolicy:
     * Cria a politica da seção
     * O Spring usa STATEFULL (que devolve a chave Token para inicio da seção,
     * mudamos para STATELESS para implementar nosso método de autenticação
     * @Bean devolve um objeto para o Spring
     * @param http
     * @return
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }
}
