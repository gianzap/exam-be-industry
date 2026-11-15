package it.msl.maintenance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// classe di configurazione: i bean qui dentro vengono registrati nel contesto Spring
@Configuration
// attiva la configurazione web security personalizzata
@EnableWebSecurity
public class SecurityConfig {

    // la SecurityFilterChain definisce i filtri di sicurezza applicati a ogni richiesta
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disattiva CSRF: serve per i form del browser, non per API REST stateless
                .csrf(csrf -> csrf.disable())
                // disattiva il form di login di default
                .formLogin(form -> form.disable())
                // disattiva l'autenticazione HTTP Basic
                .httpBasic(basic -> basic.disable())
                // nessuna sessione lato server: l'identità arriverà dal JWT
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // per ora tutto è pubblico; le regole per ruolo e il filtro JWT arrivano dopo
                .authorizeHttpRequests(h -> h.requestMatchers("/**").permitAll());
        return http.build();
    }
}
