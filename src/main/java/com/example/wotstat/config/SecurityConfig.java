package com.example.wotstat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/**").authenticated() // Користувачі повинні бути аутентифіковані для доступу до /api/**
                                .anyRequest().permitAll() // Дозвіл на доступ до всіх інших запитів без аутентифікації
                )
                .oauth2Login(oauth2Login -> // Налаштування OAuth2 для аутентифікації через Google
                        oauth2Login
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.oidcUserService(oidcUserService()) // Використання OidcUserService для отримання інформації про користувача
                                )
                                .successHandler(customAuthenticationSuccessHandler()) // Обробник успішної аутентифікації
                )
                .csrf(AbstractHttpConfigurer::disable); // Відключення CSRF захисту


        http.addFilterAfter(emailVerificationFilter(), UsernamePasswordAuthenticationFilter.class); // Додавання кастомного фільтра для перевірки email після фільтра UsernamePasswordAuthenticationFilter

        return http.build();
    }

    @Bean
    public OidcUserService oidcUserService() { // налаштування OidcUserService для отримання інформації про користувача з OIDC
        return new OidcUserService();
    }


    @Bean // обробник успішної аутентифікації, який перенаправляє на стату за клан
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();
        delegate.setDefaultTargetUrl("/clanStatisticsView.html");
        return delegate;
    }



    @Bean // кастомний фільтр для перевірки email користувача при доступі до API
    public Filter emailVerificationFilter() {
        return new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                // ініціалізація не треба
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                org.springframework.security.core.Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

                // Перевірка email тільки для запитів, що починаються з /api/
                if (httpRequest.getRequestURI().startsWith("/api/")) {
                    if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
                        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                        Map<String, Object> claims = oidcUser.getClaims();
                        String email = (String) claims.get("email");

                        // якщо email не відповідає моєму, то відмовити у доступі
                        if (!"toadkillergamer@gmail.com".equals(email)) {
                            // дозволити лише GET запити
                            if (!"GET".equalsIgnoreCase(httpRequest.getMethod())) {
                                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                                return;
                            }
                        }
                    }
                }

                // продовжити обробку запиту, якщо перевірка пройшла успішно
                chain.doFilter(request, response);
            }

            @Override
            public void destroy() {
                // завершення роботи не треба
            }
        };
    }

//   Налаштування для ігнорування певних URL-адрес (Swagger UI та API документація)
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
//    }
}
