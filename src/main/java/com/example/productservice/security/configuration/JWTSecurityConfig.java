package com.example.productservice.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class JWTSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());
        http
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/products/{id}").hasAuthority("CUSTOMER")
//                        .requestMatchers("/products").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .cors().disable()
                .csrf().disable();
        return http.build();
    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        grantedAuthoritiesConverter.setAuthoritiesClaimName("SomeRandomRole");
////        grantedAuthoritiesConverter.setAuthorityPrefix("roles");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
}