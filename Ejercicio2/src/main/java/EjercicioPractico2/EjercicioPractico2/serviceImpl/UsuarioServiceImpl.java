package EjercicioPractico2.EjercicioPractico2.serviceimpl;

import EjercicioPractico2.EjercicioPractico2.domain.Usuario;
import EjercicioPractico2.EjercicioPractico2.repository.UsuarioRepository;
import EjercicioPractico2.EjercicioPractico2.service.EmailService;
import EjercicioPractico2.EjercicioPractico2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        // Envio automatico de correo de bienvenida (Spring Mail)
        emailService.enviarCorreoBienvenida(usuarioGuardado.getEmail(), usuarioGuardado.getNombre());
        return usuarioGuardado;
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRol_NombreIgnoreCase(nombreRol);
    }

    @Override
    public List<Usuario> buscarUsuariosActivos() {
        return usuarioRepository.buscarUsuariosActivos();
    }

    @Override
    public long contarUsuariosPorRol(String nombreRol) {
        return usuarioRepository.contarUsuariosPorRol(nombreRol);
    }
}
