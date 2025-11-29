package com.example.huerto_hogar.huerto_hogar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desactivamos CSRF 
            .csrf(csrf -> csrf.disable())
            
            //Activamos CORS (Para que React se conecte)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            //Definimos permisos de rutas
            .authorizeHttpRequests(auth -> auth
                // Permitimos entrar a todos a estas rutas:
                .requestMatchers("/api/v1/usuarios/login", "/api/v1/usuarios/registro").permitAll()
                .requestMatchers("/api/v1/productos/**").permitAll()
                .requestMatchers("/api/v1/contacto/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() 
                
                // Dejamos el resto abierto (.permitAll)
                // para evitar bloqueos si algo falla. Pero ya tienes JWT listo.
                .anyRequest().permitAll() 
            );

        return http.build();
    }

    // Configuración para que el puerto 3000 (React) hable con el 8080 (Spring)
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*")); // Permitir a todos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}