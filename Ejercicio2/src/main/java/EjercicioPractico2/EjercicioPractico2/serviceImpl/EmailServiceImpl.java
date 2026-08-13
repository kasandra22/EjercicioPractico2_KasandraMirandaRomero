package EjercicioPractico2.EjercicioPractico2.serviceimpl;

import EjercicioPractico2.EjercicioPractico2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destinatario);
            mensaje.setSubject("Bienvenido(a) a MediCare");
            mensaje.setText("Hola " + nombreUsuario + ",\n\n" +
                    "Su registro en la plataforma MediCare se ha completado con exito.\n" +
                    "Ya puede iniciar sesion y utilizar nuestros servicios.\n\n" +
                    "Saludos,\nEquipo MediCare");
            javaMailSender.send(mensaje);
        } catch (Exception e) {
            
            System.err.println("No se pudo enviar el correo de bienvenida: " + e.getMessage());
        }
    }
}
