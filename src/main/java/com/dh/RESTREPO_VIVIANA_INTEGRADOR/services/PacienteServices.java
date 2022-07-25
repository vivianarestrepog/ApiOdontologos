package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;

import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteServices {
    List<Paciente> listarPacientes();
    Optional<Paciente> buscarPaciente(Long id);
    Paciente guardarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id);
}
