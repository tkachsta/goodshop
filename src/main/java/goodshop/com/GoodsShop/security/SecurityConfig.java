package goodshop.com.GoodsShop.security;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    @Qualifier("delegatedAuthenticationEntryPoint")
    private final AuthenticationEntryPoint authEntryPoint;

    @Bean
    BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       ShopUserDetailsService userDetailsService) throws Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and().build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/discounts/create", "/api/discounts/add-goods").hasRole("ADMIN")
                .requestMatchers("/api/discounts/**").permitAll()
                .requestMatchers("/api/goods/create", "/api/goods/update").hasAnyRole(new String[]{"ADMIN", "SELLER"})
                .requestMatchers("/api/goods/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/acquisitions/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/acquisitions/**").hasRole("BUYER")
                .requestMatchers("/**").permitAll()
                .and().formLogin().loginPage("/login").failureUrl("/login")
                .and().httpBasic()
                .and().exceptionHandling().authenticationEntryPoint(authEntryPoint);

        return http.build();
    }


}
