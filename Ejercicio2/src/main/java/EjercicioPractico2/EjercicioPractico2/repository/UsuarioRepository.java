package EjercicioPractico2.EjercicioPractico2.repository;

import EjercicioPractico2.EjercicioPractico2.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByRol_NombreIgnoreCase(String nombreRol);

    @Query("SELECT u FROM Usuario u WHERE u.activo = true ORDER BY u.nombre ASC")
    List<Usuario> buscarUsuariosActivos();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol.nombre = :nombreRol")
    long contarUsuariosPorRol(@Param("nombreRol") String nombreRol);
}