package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;

import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Odontologo;

import java.util.List;
import java.util.Optional;

public interface OdontologoServices {

    List<Odontologo> listarOdontologo();
    Optional<Odontologo> buscarOdontologo(Long id);
    Odontologo guardarOdontologo(Odontologo odontologo);
    Odontologo actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);
}
