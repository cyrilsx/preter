package ch.collen.preterbackendserver.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    private static final long TOKEN_VALIDITY_IN_SECONDS = 3600;

    static final String TOKEN_PREFIX = "Bearer";

    @Bean
    public AuthenticationManager authenticationManager(JWTUtil jwtUtils) {
        return new AuthenticationManager(jwtUtils);
    }

    @Bean
    public JWTUtil jwtUtils(SecurityProperties securityProperties) {
        return new JWTUtil(securityProperties.getTokenSecret(), TOKEN_VALIDITY_IN_SECONDS);
    }

    @Bean
    public SecurityContextRepository securityContextRepository(AuthenticationManager authenticationManager) {
        return new SecurityContextRepository(authenticationManager);
    }

    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder(SecurityProperties securityProperties) {
        return new Pbkdf2PasswordEncoder(securityProperties.getHashSecret(), 100, 128, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            SecurityContextRepository securityContextRepository,
            AuthenticationManager authenticationManager,
            ServerHttpSecurity http) {
        return http
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/invitations").permitAll()
                        .pathMatchers("/api/invitations/**").permitAll()
                        .pathMatchers("/api/login").permitAll()
                        .pathMatchers("/api/register").permitAll()
                        .pathMatchers("/api/**").authenticated()
                        .anyExchange().authenticated()
                )
                .build();
    }

}
