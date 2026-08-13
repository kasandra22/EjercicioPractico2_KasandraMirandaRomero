package EjercicioPractico2.EjercicioPractico2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                         Authentication authentication) throws IOException, ServletException {

        String rol = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("");

        String destino;
        switch (rol) {
            case "ROLE_ADMIN" -> destino = "/usuarios";
            case "ROLE_MEDICO" -> destino = "/citas";
            case "ROLE_PACIENTE" -> destino = "/citas";
            default -> destino = "/";
        }

        response.sendRedirect(request.getContextPath() + destino);
    }
}
