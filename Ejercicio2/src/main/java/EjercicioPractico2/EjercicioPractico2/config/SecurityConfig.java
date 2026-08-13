package EjercicioPractico2.EjercicioPractico2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    /**
     * Codificador de contrasenas. El script de base de datos del curso inserta
     * contrasenas en texto plano ('12345'), por lo que se utiliza un encoder
     * que compara texto plano. Para produccion se recomienda BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainTextPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                .requestMatchers("/", "/login").permitAll()
                .requestMatchers("/usuarios/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/roles/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/citas/nuevo", "/citas/guardar", "/citas/editar/**", "/citas/eliminar/**")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_MEDICO")
                .requestMatchers("/citas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEDICO", "ROLE_PACIENTE")
                .requestMatchers("/consultas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MEDICO")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .userDetailsService(usuarioDetailsService)
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
