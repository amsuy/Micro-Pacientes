package com.registro.pacientes.repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.registro.pacientes.entidades.Paciente;

@Repository
public interface pacienterepositorio extends JpaRepository<Paciente, Long> {

    // Consultar todos los pacientes
    @SuppressWarnings("null")
    List<Paciente> findAll();

    // Consultar por ID de paciente
    Optional<Paciente> findByIdpaciente(Long idpaciente);

    // Consultar por nombre completo
    List<Paciente> findByNombrecompletoContainingIgnoreCase(String nombrecompleto);

    // Consultar por NIT
    List<Paciente> findByNit(Long nit);

    // Consultar por CUI
    List<Paciente> findByCui(Long cui);

    // Consultar por dirección
    List<Paciente> findByDireccionContainingIgnoreCase(String direccion);

    // Consultar por teléfono
    List<Paciente> findByTelefono(Integer telefono);

    // Consultar por fecha de registro
    List<Paciente> findByFecha(LocalDate fecha);

    // Consultar por estado "ACTIVO"
    List<Paciente> findByEstado(String estado);

    // Consultar por estado "INACTIVO"
    List<Paciente> findByEstadoNot(String estado);

    // Actualizar todos los datos excepto idpaciente y fecha

    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.nombrecompleto = :nombre, p.edad = :edad, p.nit = :nit, p.cui = :cui, p.direccion = :direccion, p.telefono = :telefono WHERE p.idpaciente = :idpaciente")
    void actualizarPaciente(
            @Param("idpaciente") Long idpaciente,
            @Param("nombre") String nombre,
            @Param("edad") int edad,
            @Param("nit") long nit,
            @Param("cui") long cui,
            @Param("direccion") String direccion,
            @Param("telefono") Integer telefono);

    // Actualizar solo el teléfono
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.telefono = :telefono WHERE p.idpaciente = :idpaciente")
    void actualizarTelefono(@Param("idpaciente") Long idpaciente, @Param("telefono") Integer telefono);

    // Actualizar solo la dirección
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.direccion = :direccion WHERE p.idpaciente = :idpaciente")
    void actualizarDireccion(@Param("idpaciente") Long idpaciente, @Param("direccion") String direccion);

    // Actualizar solo el NIT
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.nit = :nit WHERE p.idpaciente = :idpaciente")
    void actualizarNit(@Param("idpaciente") Long idpaciente, @Param("nit") long nit);

    // Actualizar solo el CUI
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.cui = :cui WHERE p.idpaciente = :idpaciente")
    void actualizarCui(@Param("idpaciente") Long idpaciente, @Param("cui") long cui);

    // Actualizar solo la edad
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.edad = :edad WHERE p.idpaciente = :idpaciente")
    void actualizarEdad(@Param("idpaciente") Long idpaciente, @Param("edad") int edad);

    // Actualizar solo el nombre
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.nombrecompleto = :nombre WHERE p.idpaciente = :idpaciente")
    void actualizarNombre(@Param("idpaciente") Long idpaciente, @Param("nombre") String nombre);

    // Borrado lógico - cambiar estado a "INACTIVO"
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.estado = 'INACTIVO' WHERE p.idpaciente = :id")
    void borrarPaciente(@Param("id") Long id);

    // Restaurar paciente - cambiar estado a "ACTIVO"
    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.estado = 'ACTIVO' WHERE p.idpaciente = :id")
    void restaurarPaciente(@Param("id") Long id);
}
