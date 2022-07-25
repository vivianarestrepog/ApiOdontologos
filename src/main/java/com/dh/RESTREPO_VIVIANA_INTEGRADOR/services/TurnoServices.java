package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoServices {
    List<Turno> listarTurnos();
    Optional<Turno> buscarTurno(Long id);
    Turno guardarTurno(Turno turno);
    Turno actualizarTurno(Turno turno);
    void eliminarTurno(Long id);
}
