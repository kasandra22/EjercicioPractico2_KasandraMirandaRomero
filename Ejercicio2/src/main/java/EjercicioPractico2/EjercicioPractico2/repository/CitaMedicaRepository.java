package EjercicioPractico2.EjercicioPractico2.repository;

import EjercicioPractico2.EjercicioPractico2.domain.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {

    List<CitaMedica> findByActiva(Boolean activa);

    List<CitaMedica> findByFechaBetween(LocalDate desde, LocalDate hasta);

    List<CitaMedica> findByEspecialidadContainingIgnoreCase(String especialidad);

    @Query("SELECT COUNT(c) FROM CitaMedica c WHERE c.activa = true")
    long contarCitasActivas();
}