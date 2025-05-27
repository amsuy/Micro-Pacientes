package com.registro.pacientes.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.pacientes.dto.PacienteDTO;
import com.registro.pacientes.entidades.Paciente;
import com.registro.pacientes.repositorio.pacienterepositorio;

@Service
public class PacienteServicio {

    private final pacienterepositorio pacienteRepositorio;

    public PacienteServicio(pacienterepositorio pacienteRepositorio) {
        this.pacienteRepositorio = pacienteRepositorio;
    }

    // Listar todos los pacientes
    public List<Paciente> consultarPacientes() {
        return pacienteRepositorio.findAll();
    }

    // Registrar un paciente
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }

    // Consultar paciente por ID
    public Optional<Paciente> buscarPorId(Long idpaciente) {
        return pacienteRepositorio.findByIdpaciente(idpaciente);
    }

    // Consultas personalizadas
    public List<Paciente> buscarPorNombre(String nombrecompleto) {
        return pacienteRepositorio.findByNombrecompletoContainingIgnoreCase(nombrecompleto);
    }

    public List<Paciente> buscarPorDireccion(String direccion) {
        return pacienteRepositorio.findByDireccionContainingIgnoreCase(direccion);
    }

    public List<Paciente> buscarPorNit(Long nit) { 
        return pacienteRepositorio.findByNit(nit);
    }
    
    public List<Paciente> buscarPorCui(Long cui) { 
        return pacienteRepositorio.findByCui(cui);
    }
    

    public List<Paciente> buscarPorTelefono(Integer telefono) {
        return pacienteRepositorio.findByTelefono(telefono);
    }

    public List<Paciente> buscarPorFecha(LocalDate fecha) {
        return pacienteRepositorio.findByFecha(fecha);
    }

    public List<Paciente> buscarPorEstadoActivo() {
        return pacienteRepositorio.findByEstado("ACTIVO");
    }

    public List<Paciente> buscarPorEstadoInactivo() {
        return pacienteRepositorio.findByEstado("INACTIVO");
    }

  

    // Actualizar datos específicos
    @Transactional
    public void actualizarTelefono(Long idpaciente, Integer telefono) {
        pacienteRepositorio.actualizarTelefono(idpaciente, telefono);
    }

    @Transactional
    public void actualizarDireccion(Long idpaciente, String direccion) {
        pacienteRepositorio.actualizarDireccion(idpaciente, direccion);
    }

    @Transactional
    public void actualizarNit(Long idpaciente, long nit) {
        pacienteRepositorio.actualizarNit(idpaciente, nit);
    }

    @Transactional
    public void actualizarCui(Long idpaciente, long cui) {
        pacienteRepositorio.actualizarCui(idpaciente, cui);
    }

    @Transactional
    public void actualizarEdad(Long idpaciente, int edad) {
        pacienteRepositorio.actualizarEdad(idpaciente, edad);
    }

    @Transactional
    public void actualizarNombre(Long idpaciente, String nombrecompleto) {
        pacienteRepositorio.actualizarNombre(idpaciente, nombrecompleto);
    }

    // Borrado lógico - cambiar estado a "INACTIVO"
    @Transactional
    public void borrarPaciente(Long idpaciente) {
        pacienteRepositorio.borrarPaciente(idpaciente);
    }

    // Restaurar paciente - cambiar estado a "ACTIVO"
    @Transactional
    public void restaurarPaciente(Long idpaciente) {
        pacienteRepositorio.restaurarPaciente(idpaciente);
    }


    // Actualizar Paciente pero no id paciente ni fecha ni estado
    @Transactional
    public void actualizarPaciente(Long idpaciente, PacienteDTO pacienteDto) {
        Optional<Paciente> pacienteOptional = pacienteRepositorio.findById(idpaciente);
        
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            
            paciente.setNombrecompleto(pacienteDto.getNombrecompleto());
            paciente.setEdad(pacienteDto.getEdad());
            paciente.setNit(pacienteDto.getNit());
            paciente.setCui(pacienteDto.getCui());
            paciente.setDireccion(pacienteDto.getDireccion());
            paciente.setTelefono(pacienteDto.getTelefono());
    
            // 🔹 Estado se mantiene sin cambios automáticamente
            pacienteRepositorio.save(paciente);
        } else {
            throw new RuntimeException("Paciente no encontrado con ID: " + idpaciente);
        }
    }
    

}