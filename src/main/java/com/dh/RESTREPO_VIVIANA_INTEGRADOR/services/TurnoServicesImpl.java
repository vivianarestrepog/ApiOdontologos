package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Turno;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServicesImpl implements TurnoServices {

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarTurno(Long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Turno actualizarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
