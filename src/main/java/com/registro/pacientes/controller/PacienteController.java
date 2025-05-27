package com.registro.pacientes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.pacientes.dto.PacienteDTO;
import com.registro.pacientes.entidades.Paciente;
import com.registro.pacientes.servicio.PacienteServicio;

@RestController
@RequestMapping("/api/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteServicio pacienteServicio;

    public PacienteController(PacienteServicio pacienteServicio) {
        this.pacienteServicio = pacienteServicio;
    }

    // Listar todos los pacientes
    @GetMapping("/consultar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteServicio.consultarPacientes());
    }

    // Registrar un paciente
    @PostMapping("/ingresar")
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody PacienteDTO pacienteDto) {
        Paciente paciente = new Paciente(
                null,
                pacienteDto.getNombrecompleto(),
                pacienteDto.getEdad(),
                pacienteDto.getNit(),
                pacienteDto.getCui(),
                pacienteDto.getDireccion(),
                pacienteDto.getTelefono(),
                LocalDate.now(), // Se asigna automáticamente la fecha actual
                "ACTIVO" // Estado siempre será ACTIVO al registrar
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteServicio.registrarPaciente(paciente));
    }

    @GetMapping("/buscar/id/{id}")
public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable("id") Long id) {
    return ResponseEntity.ok(pacienteServicio.buscarPorId(id));
}

@GetMapping("/buscar/nit/{nit}")
public ResponseEntity<List<Paciente>> buscarPorNit(@PathVariable("nit") Long nit) {
    return ResponseEntity.ok(pacienteServicio.buscarPorNit(nit));
}


@GetMapping("/buscar/cui/{cui}")
public ResponseEntity<List<Paciente>> buscarPorCui(@PathVariable("cui") Long cui) {
    return ResponseEntity.ok(pacienteServicio.buscarPorCui(cui));
}


@GetMapping("/buscar/direccion/{direccion}")
public ResponseEntity<List<Paciente>> buscarPorDireccion(@PathVariable("direccion") String direccion) {
    return ResponseEntity.ok(pacienteServicio.buscarPorDireccion(direccion));
}


@GetMapping("/buscar/telefono/{telefono}")
public ResponseEntity<List<Paciente>> buscarPorTelefono(@PathVariable("telefono") Integer telefono) {
    return ResponseEntity.ok(pacienteServicio.buscarPorTelefono(telefono));
}


@GetMapping("/buscar/fecha/{fecha}")
public ResponseEntity<List<Paciente>> buscarPorFecha(@PathVariable("fecha") LocalDate fecha) {
    return ResponseEntity.ok(pacienteServicio.buscarPorFecha(fecha));
}

@GetMapping("/buscar/nombre/{nombre}")
public ResponseEntity<List<Paciente>> buscarPorNombre(@PathVariable("nombre") String nombre) {
    return ResponseEntity.ok(pacienteServicio.buscarPorNombre(nombre));
}



    // Actualizar solo un campo específico
    @PutMapping("/actualizar/telefono/{id}/{telefono}")
    public ResponseEntity<?> actualizarTelefono(@PathVariable Long id, @PathVariable Integer telefono) {
        pacienteServicio.actualizarTelefono(id, telefono);
        return ResponseEntity.ok("Teléfono actualizado correctamente");
    }

    @PutMapping("/actualizar/direccion/{id}/{direccion}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Long id, @PathVariable String direccion) {
        pacienteServicio.actualizarDireccion(id, direccion);
        return ResponseEntity.ok("Dirección actualizada correctamente");
    }

    @PutMapping("/actualizar/nit/{id}/{nit}")
    public ResponseEntity<?> actualizarNit(@PathVariable Long id, @PathVariable int nit) {
        pacienteServicio.actualizarNit(id, nit);
        return ResponseEntity.ok("NIT actualizado correctamente");
    }

    @PutMapping("/actualizar/cui/{id}/{cui}")
    public ResponseEntity<?> actualizarCui(@PathVariable Long id, @PathVariable int cui) {
        pacienteServicio.actualizarCui(id, cui);
        return ResponseEntity.ok("CUI actualizado correctamente");
    }

    @PutMapping("/actualizar/edad/{id}/{edad}")
    public ResponseEntity<?> actualizarEdad(@PathVariable Long id, @PathVariable int edad) {
        pacienteServicio.actualizarEdad(id, edad);
        return ResponseEntity.ok("Edad actualizada correctamente");
    }

    @PutMapping("/actualizar/nombre/{id}/{nombre}")
    public ResponseEntity<?> actualizarNombre(@PathVariable Long id, @PathVariable String nombre) {
        pacienteServicio.actualizarNombre(id, nombre);
        return ResponseEntity.ok("Nombre actualizado correctamente");
    }

    @PutMapping("/borrar/{id}")
    public ResponseEntity<?> borrarPaciente(@PathVariable("id") Long id) {
        pacienteServicio.borrarPaciente(id);
        return ResponseEntity.ok("Paciente marcado como INACTIVO");
    }

    // Restaurar paciente
    @PutMapping("/restaurar/{id}")
public ResponseEntity<?> restaurarPaciente(@PathVariable("id") Long id) {
    pacienteServicio.restaurarPaciente(id);
    return ResponseEntity.ok("Paciente restaurado a ACTIVO");
}


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable("id") Long id, @RequestBody PacienteDTO pacienteDto) {
        pacienteServicio.actualizarPaciente(id, pacienteDto);
        return ResponseEntity.ok("Paciente actualizado correctamente");
    }

    // Método simplificado específicamente para Feign
    @GetMapping("/buscar/resumen/{id}")
    public ResponseEntity<PacienteDTO> obtenerPacienteResumenPorId(@PathVariable("id") Long id) {
        Optional<Paciente> pacienteOptional = pacienteServicio.buscarPorId(id);

        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();

            PacienteDTO pacienteDTO = new PacienteDTO(
                    paciente.getIdpaciente(),
                    paciente.getNombrecompleto(),
                    paciente.getNit(),
                    paciente.getCui(),
                    paciente.getDireccion(),
                    paciente.getTelefono());

            return ResponseEntity.ok(pacienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
