package EjercicioPractico2.EjercicioPractico2.controller;

import EjercicioPractico2.EjercicioPractico2.service.CitaMedicaService;
import EjercicioPractico2.EjercicioPractico2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final CitaMedicaService citaMedicaService;
    private final UsuarioService usuarioService;

    @Autowired
    public ConsultaController(CitaMedicaService citaMedicaService, UsuarioService usuarioService) {
        this.citaMedicaService = citaMedicaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String vistaConsultas(Model model) {
        model.addAttribute("totalCitasActivas", citaMedicaService.contarCitasActivas());
        return "consultas";
    }

    @GetMapping("/por-estado")
    public String porEstado(@RequestParam(defaultValue = "true") boolean activa, Model model) {
        model.addAttribute("resultadoCitas", citaMedicaService.buscarPorEstado(activa));
        model.addAttribute("tipoConsulta", "Citas " + (activa ? "activas" : "inactivas"));
        return "consultas";
    }

    @GetMapping("/por-fecha")
    public String porFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
                            Model model) {
        model.addAttribute("resultadoCitas", citaMedicaService.buscarPorRangoFechas(desde, hasta));
        model.addAttribute("tipoConsulta", "Citas entre " + desde + " y " + hasta);
        return "consultas";
    }

    @GetMapping("/por-especialidad")
    public String porEspecialidad(@RequestParam String especialidad, Model model) {
        model.addAttribute("resultadoCitas", citaMedicaService.buscarPorEspecialidad(especialidad));
        model.addAttribute("tipoConsulta", "Citas con especialidad: " + especialidad);
        return "consultas";
    }

    @GetMapping("/por-rol")
    public String porRol(@RequestParam String rol, Model model) {
        model.addAttribute("resultadoUsuarios", usuarioService.buscarPorRol(rol));
        model.addAttribute("tipoConsulta", "Usuarios con rol: " + rol);
        return "consultas";
    }
}
