package EjercicioPractico2.EjercicioPractico2.serviceimpl;

import EjercicioPractico2.EjercicioPractico2.domain.CitaMedica;
import EjercicioPractico2.EjercicioPractico2.repository.CitaMedicaRepository;
import EjercicioPractico2.EjercicioPractico2.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    private final CitaMedicaRepository citaMedicaRepository;

    @Autowired
    public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
    }

    @Override
    public List<CitaMedica> listarTodas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public Optional<CitaMedica> buscarPorId(Long id) {
        return citaMedicaRepository.findById(id);
    }

    @Override
    public CitaMedica guardar(CitaMedica cita) {
        return citaMedicaRepository.save(cita);
    }

    @Override
    public void eliminar(Long id) {
        citaMedicaRepository.deleteById(id);
    }

    @Override
    public List<CitaMedica> buscarPorEstado(boolean activa) {
        return citaMedicaRepository.findByActiva(activa);
    }

    @Override
    public List<CitaMedica> buscarPorRangoFechas(LocalDate desde, LocalDate hasta) {
        return citaMedicaRepository.findByFechaBetween(desde, hasta);
    }

    @Override
    public List<CitaMedica> buscarPorEspecialidad(String especialidad) {
        return citaMedicaRepository.findByEspecialidadContainingIgnoreCase(especialidad);
    }

    @Override
    public long contarCitasActivas() {
        return citaMedicaRepository.contarCitasActivas();
    }
}
