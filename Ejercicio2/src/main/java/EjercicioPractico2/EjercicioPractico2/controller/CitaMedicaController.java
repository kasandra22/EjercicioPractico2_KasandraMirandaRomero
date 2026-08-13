package EjercicioPractico2.EjercicioPractico2.controllers;

import EjercicioPractico2.EjercicioPractico2.domain.CitaMedica;
import EjercicioPractico2.EjercicioPractico2.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/citas")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;

    @Autowired
    public CitaMedicaController(CitaMedicaService citaMedicaService) {
        this.citaMedicaService = citaMedicaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citaMedicaService.listarTodas());
        return "citas/list";
    }

    @GetMapping("/nuevo")
    public String nuevoFormulario(Model model) {
        model.addAttribute("cita", new CitaMedica());
        return "citas/form";
    }

    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        CitaMedica cita = citaMedicaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada: " + id));
        model.addAttribute("cita", cita);
        return "citas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CitaMedica cita) {
        citaMedicaService.guardar(cita);
        return "redirect:/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citaMedicaService.eliminar(id);
        return "redirect:/citas";
    }
}
