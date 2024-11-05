package com.lorem.ExamsManagement.security.config;


import com.lorem.ExamsManagement.model.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(SessionManag -> SessionManag.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(filter , UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authconfig -> {
                            authconfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST, "/auth/RegisterAdmin").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST, "/auth/RegisterStaff").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST,"/element/*").permitAll();
                            authconfig.requestMatchers(HttpMethod.PUT,"/element/update/{id}").permitAll();
                            authconfig.requestMatchers(HttpMethod.DELETE,"/element/delete/{id}").permitAll();
                            authconfig.requestMatchers(HttpMethod.GET,"/element/all").permitAll();
                            authconfig.requestMatchers(HttpMethod.GET,"/element/module/{moduleId}").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST,"/module/*").permitAll();
                            authconfig.requestMatchers(HttpMethod.GET,"/module/*").permitAll();
                            authconfig.requestMatchers("/error").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST,"/department").hasAuthority(Permissions.EDIT.name());
                            authconfig.requestMatchers(HttpMethod.GET,"/department/{id}").permitAll();
                            authconfig.requestMatchers(HttpMethod.GET, "/department/all").permitAll();
                            authconfig.requestMatchers(HttpMethod.GET,"/staff").permitAll();
                            authconfig.requestMatchers(HttpMethod.POST,"/staff").hasAuthority(Permissions.EDIT.name());
                            authconfig.anyRequest().permitAll();
                        }
                );
        return http.build();
    }
}
