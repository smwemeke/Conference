package com.pluralsight.conference;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConferenceSecurityConfig {
    private DataSource dataSource;
    @Autowired
    public  ConferenceSecurityConfig(DataSource dataSource){
        this.dataSource = dataSource;

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/anonymous*").anonymous()
                        .requestMatchers("/login*").permitAll()
                        .requestMatchers("/assets/css/**", "/assets/js/**", "/images/**").permitAll()
                        .requestMatchers("/index*").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                );
        return http.build();
    }

    /*Configures using InMemory Authentication*/
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("pass"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    @Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                //.jdbcAuthentication().dataSource(dataSource);
                .ldapAuthentication()
                .userDnPatterns("uid={0}, ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                   .url("ldap://localhost:8389/dc=pluralsight,dc=com")
                   .and()
                .passwordCompare()
                   .passwordEncoder(new BCryptPasswordEncoder())
                   .passwordAttribute("userPassword");
    }

}