package EjercicioPractico2.EjercicioPractico2.service;

import EjercicioPractico2.EjercicioPractico2.domain.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> listarTodos();

    Optional<Rol> buscarPorId(Long id);

    Rol guardar(Rol rol);

    void eliminar(Long id);

    Optional<Rol> buscarPorNombre(String nombre);
}
