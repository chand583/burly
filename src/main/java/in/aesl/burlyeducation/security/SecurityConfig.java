package in.aesl.burlyeducation.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
public class SecurityConfig {
 
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return new ShopmeUserDetailsService();
    // }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/welcome/**").permitAll()
                .antMatchers("/css/**","/assets/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/users/**", "/settings/**").hasAuthority("Admin");
              //  .hasAnyAuthority("Admin", "Editor", "Salesperson")
              //  .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                // .anyRequest().authenticated()
                // .and().formLogin()
                // .loginPage("/login")
                // .usernameParameter("email")
                // .permitAll()
                // .and()
                // .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                // .and()
                // .logout().permitAll();
 
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
 
}