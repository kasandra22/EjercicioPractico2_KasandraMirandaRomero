package EjercicioPractico2.EjercicioPractico2.service;

import EjercicioPractico2.EjercicioPractico2.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorId(Long id);

    Usuario registrar(Usuario usuario);

    Usuario actualizar(Usuario usuario);

    void eliminar(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> buscarPorRol(String nombreRol);

    List<Usuario> buscarUsuariosActivos();

    long contarUsuariosPorRol(String nombreRol);
}
