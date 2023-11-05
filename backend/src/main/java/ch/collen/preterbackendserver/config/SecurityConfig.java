package ch.collen.preterbackendserver.config;

import ch.collen.preterbackendserver.db.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.Near;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private static final long TOKEN_VALIDITY_IN_SECONDS = 3600;

    static final String TOKEN_PREFIX = "Bearer";
    private static final  String TOKEN_SECRET = "KrxaxUxzHv7*cGC%AhFga&NECh99aaLgThyH^Jax^Q4MCks%PF";


    @Bean
    public AuthenticationManager authenticationManager(JWTUtil jwtUtils) {
        return new AuthenticationManager(jwtUtils);
    }

    @Bean
    public JWTUtil jwtUtils() {
        return new JWTUtil(TOKEN_SECRET, TOKEN_VALIDITY_IN_SECONDS + "s");
    }

    @Bean
    public SecurityContextRepository securityContextRepository(AuthenticationManager authenticationManager) {
        return new SecurityContextRepository(authenticationManager);
    }

     @Bean
     public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
         return new Pbkdf2PasswordEncoder(TOKEN_SECRET, 10000, 128, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
     }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            SecurityContextRepository securityContextRepository,
            AuthenticationManager authenticationManager,
            ServerHttpSecurity http) {
        return http
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/users").permitAll()
                        .pathMatchers("/api/users/**").permitAll()
                        .pathMatchers("/api/login").permitAll()
                        .pathMatchers("/api/**").authenticated()
                        .anyExchange().authenticated()
                )
                .build();
    }
/*
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.

                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
*/
//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
//        return (username) -> userRepository.findById(username)
//                .map(u -> User.withUsername(u.email())
//                        .password(u.password())
//                        .roles(u.roles().stream().map(Enum::name).toList().toArray(new String[2]))
//                        .accountLocked(false)
//                        .build()
//                );
//    }


}
