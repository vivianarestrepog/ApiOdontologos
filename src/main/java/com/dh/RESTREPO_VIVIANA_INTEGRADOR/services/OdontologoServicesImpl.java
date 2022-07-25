package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Odontologo;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServicesImpl implements OdontologoServices {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public List<Odontologo> listarOdontologo() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarOdontologo(Long id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
}
