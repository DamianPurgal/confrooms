package pl.polsl.confrooms.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.polsl.confrooms.model.User.UserService;
//KONFIGURACJA SPRING SECURITY
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        TO JEST PLIK KONFIGURACYJNY. AKTUALNIE WSZYSTKIE ZABEZPIECZENIA SA WYLACZONE.
//        SKUTKUJE TO TYM, ZE KAZDY UZYTKOWNIK MOZE WEJSC W KAZDA CZESC APLIKACJI.
//        ZROBILEM TO DLA UPROSZCZENIA PISANIA KODU.
//        JESLI CHCESZ WLACZYC ZABEZPIECZENIA NAPISZ DO MNIE - POMOGE:)
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**")
                    .permitAll()
                .antMatchers("/user/registration")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
