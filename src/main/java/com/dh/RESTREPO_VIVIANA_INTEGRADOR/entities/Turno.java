package com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Turnos")
public class Turno {
    @Id
    @SequenceGenerator(name = "turno_sequence",sequenceName = "turno_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "turno_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column
    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public Turno(Odontologo odontologo, Paciente paciente, LocalDate fecha) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
    }

    public Turno() {
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
