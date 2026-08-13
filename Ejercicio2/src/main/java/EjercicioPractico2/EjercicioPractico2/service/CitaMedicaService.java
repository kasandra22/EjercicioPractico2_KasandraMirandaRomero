package EjercicioPractico2.EjercicioPractico2.service;

import EjercicioPractico2.EjercicioPractico2.domain.CitaMedica;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaMedicaService {

    List<CitaMedica> listarTodas();

    Optional<CitaMedica> buscarPorId(Long id);

    CitaMedica guardar(CitaMedica cita);

    void eliminar(Long id);

    List<CitaMedica> buscarPorEstado(boolean activa);

    List<CitaMedica> buscarPorRangoFechas(LocalDate desde, LocalDate hasta);

    List<CitaMedica> buscarPorEspecialidad(String especialidad);

    long contarCitasActivas();
}
