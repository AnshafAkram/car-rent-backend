package org.example.config;

import org.example.security.JwtAuthenticationFilter;
import org.example.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailsService customUserDetailsService;



    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomUserDetailsService customUserDetailsService
    ) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

        this.customUserDetailsService = customUserDetailsService;

    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }



    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {

        return configuration.getAuthenticationManager();

    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {


        http

                // Disable CSRF for REST API
                .csrf(csrf -> csrf.disable())


                // Angular CORS
                .cors(cors -> cors.configurationSource(request -> {

                    CorsConfiguration config = new CorsConfiguration();

                    config.addAllowedOrigin("http://localhost:4200");

                    config.addAllowedHeader("*");

                    config.addAllowedMethod("*");

                    config.setAllowCredentials(true);

                    return config;

                }))



                // JWT = Stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )



                .userDetailsService(customUserDetailsService)



                .authorizeHttpRequests(auth -> auth


                        // =========================
                        // PUBLIC
                        // =========================

                        .requestMatchers("/api/auth/**")
                        .permitAll()



                        // =========================
                        // ADMIN ONLY
                        // =========================

                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")



                        // =========================
                        // VEHICLES
                        // ADMIN + CUSTOMER
                        // =========================

                        .requestMatchers("/api/vehicles/**")
                        .hasAnyRole(
                                "ADMIN",
                                "CUSTOMER"
                        )



                        // =========================
                        // BOOKINGS
                        // ADMIN + CUSTOMER
                        // =========================

                        .requestMatchers("/api/bookings/**")
                        .hasAnyRole(
                                "ADMIN",
                                "CUSTOMER"
                        )



                        // =========================
                        // PAYMENTS
                        // ADMIN + CUSTOMER
                        // =========================

                        .requestMatchers("/api/payments/**")
                        .hasAnyRole(
                                "ADMIN",
                                "CUSTOMER"
                        )



                        // Everything else requires login
                        .anyRequest()
                        .authenticated()

                )



                .httpBasic(Customizer.withDefaults());




        // JWT Filter
        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );



        return http.build();

    }

}