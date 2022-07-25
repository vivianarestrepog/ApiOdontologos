package com.dh.RESTREPO_VIVIANA_INTEGRADOR.controller;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Turno;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.BadRequestException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.ResourceNotFoundException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.services.OdontologoServices;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.services.PacienteServices;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.services.TurnoServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(TurnoController.class);

    @Autowired
    private TurnoServices turnoServices;
    @Autowired
    private PacienteServices pacienteServices;
    @Autowired
    private OdontologoServices odontologoServices;

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        logger.info("Se listaron los turnos satisfactoriamente");
        return ResponseEntity.ok(turnoServices.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> traerTurnoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoServices.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            logger.info("Se pudo mostrar el turno correctamente");
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            logger.error("No se encontró el turno con el id " + id);
            throw new ResourceNotFoundException("No se encontró el turno con id=" + id);
        }
    }


    @PostMapping
    public ResponseEntity<Turno> registrarNuevoTurno(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoServices.guardarTurno(turno));
    }

    /*
    @PostMapping
    public ResponseEntity<Turno> registrarNuevoTurno(@RequestBody Turno turno) throws BadRequestException {
        try {
            Turno turnoRegistrado = turnoServices.guardarTurno(turno);
            logger.info("Se guardó el turno exitosamente");
            return ResponseEntity.ok(turnoRegistrado);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new BadRequestException(exception.getMessage());
        }
    }
     */

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoParaActualizar = turnoServices.buscarTurno(turno.getId());
        if (turnoParaActualizar.isPresent()){
            logger.info("El turno se ha actualizado correctamente");
            return ResponseEntity.ok(turnoServices.actualizarTurno(turno));
        }
        else {
            logger.info("Error al actualizar el turno.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoABorrar = turnoServices.buscarTurno(id);
        if (turnoABorrar.isPresent()){
            turnoServices.eliminarTurno(id);
            logger.info("Se eliminó el turno con id: " + id + " correctamente.");
            return ResponseEntity.ok("Se eliminó el turno con ID = " + id);
        }
        else {
            logger.error("Error al ingresar el id del turno para eliminarlo");
            throw new ResourceNotFoundException("No se encontró el turno con " + "id=" +id +
                    " Ingrese el ID correcto para realizar su eliminación.");
        }
    }


}
