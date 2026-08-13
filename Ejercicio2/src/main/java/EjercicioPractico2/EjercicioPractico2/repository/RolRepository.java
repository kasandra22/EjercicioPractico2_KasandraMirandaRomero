package EjercicioPractico2.EjercicioPractico2.repository;

import EjercicioPractico2.EjercicioPractico2.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);
}