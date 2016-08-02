package com.webstore.web.config;

import com.webstore.web.filter.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oles on 7/26/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan({"com.webstore.common"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
//                .antMatchers("/", "/home", "/app/**", "/css/**", "/js/**", "/logout").permitAll()
//                .anyRequest().authenticated().and()
                .anyRequest().permitAll().and()
//                .logout().logoutSuccessHandler(getLogoutSuccessHandler()).invalidateHttpSession(true).and()
                .logout().logoutSuccessUrl("/").and()
                .csrf().csrfTokenRepository(csrfTokenRepository()).and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
//                .accessDeniedPage("/app/index.html/403");

        http.rememberMe()
                .rememberMeParameter("remember-me-param")
                .rememberMeCookieName("my-remember-me")
                .rememberMeServices(new RememberMeServices() {
                    @Override
                    public Authentication autoLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
                        return null;
                    }

                    @Override
                    public void loginFail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
                        httpServletRequest.getSession().invalidate();
                    }

                    @Override
                    public void loginSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                        httpServletRequest.getSession().setMaxInactiveInterval(200000);
                    }
                })
                .tokenValiditySeconds(30);
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            httpServletResponse.setStatus(401);
//            httpServletResponse.setHeader("Location", "/app/index.html");
//            httpServletResponse.sendRedirect("/app/index.html");
//            httpServletRequest.getRequestDispatcher("/app/index.html").forward(httpServletRequest, httpServletResponse);
//            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "/app/index.html");
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean(name = "logoutSuccessHandler")
    public LogoutSuccessHandler getLogoutSuccessHandler() {
        return new MyLogoutSuccessHandler();
    }

    public class MyLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
            // maybe do some other things ...
            super.handle(request, response, authentication);
        }
    }
}
