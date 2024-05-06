package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    /* Liberar a requisição sem um token.*/
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    /* É necessária a liberação dos três endereços abaixo, para visualizar a documentação
                       gerada pelo SpringDoc */
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated();   /*Exigir que as demais requisições, sejam autenticadas.*/
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)  /*Forma de indicar a ordem dos filtros na aplicação.*/
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /* Informando ao Spring que a nossa API vai utilizar o BCrypt como algoritmo de hashing de senhas. */
    }

}

    /* Outra maneira de restringir o acesso a determinadas funcionalidades, com base no perfil dos usuários,
    é com a utilização de um recurso do Spring Security conhecido como Method Security, que funciona com a
    utilização de anotações em métodos: */
/*
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }*/

   /* No exemplo de código anterior o método foi anotado com @Secured("ROLE_ADMIN"), para que apenas usuários
    com o perfil ADMIN possam disparar requisições para detalhar um médico. A anotação @Secured pode ser
    adicionada em métodos individuais ou mesmo na classe, que seria o equivalente a adicioná-la em todos os métodos.

    Atenção! Por padrão esse recurso vem desabilitado no spring Security, sendo que para o utilizar devemos adicionar
    a seguinte anotação na classe Securityconfigurations do projeto: */

    /*@EnableMethodSecurity(securedEnabled = true)*/

    /*
    Você pode conhecer mais detalhes sobre o recurso de method security na documentação do Spring Security,
    disponível em:
        https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
    */
