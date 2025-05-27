package com.registro.pacientes.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaciente")
    private Long idpaciente;
    
    @Column(name = "nombrecompleto", length = 500, nullable = false)
    private String nombrecompleto;
    
    @Column(name = "edad", nullable = false)
    private int edad;
    
    @Column(name = "nit", nullable = false)
    private long nit;
    
    @Column(name = "cui", nullable = false)
    private long cui;    
    
    @Column(name = "direccion", length = 500)
    private String direccion;
    
    @Column(name = "telefono")
    private Integer telefono;
    
    @Column(name = "fecha", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate fecha;
    
    @Column(name = "estado", length = 10, nullable = false)
    private String estado = "ACTIVO";
}