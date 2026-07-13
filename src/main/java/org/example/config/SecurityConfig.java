package org.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }



    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http
                .csrf(csrf -> csrf.disable())


                .cors(cors -> cors.configurationSource(request -> {

                    CorsConfiguration config = new CorsConfiguration();

                    config.addAllowedOrigin(
                            "http://localhost:4200"
                    );

                    config.addAllowedHeader("*");

                    config.addAllowedMethod("*");

                    config.setAllowCredentials(true);


                    return config;

                }))


                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()


                        // Everything else temporarily open
                        // We will protect after JWT
                        .anyRequest().permitAll()

                );


        return http.build();

    }

}