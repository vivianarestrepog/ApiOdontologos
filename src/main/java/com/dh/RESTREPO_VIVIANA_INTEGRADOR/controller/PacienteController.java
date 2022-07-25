package com.dh.RESTREPO_VIVIANA_INTEGRADOR.controller;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Paciente;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.BadRequestException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.ResourceNotFoundException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.services.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteServiceImpl pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> traerPacientes() {
        logger.info("Se listaron los pacientes satisfactoriamente");
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> traerPacientePorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()) {
            logger.info("Se pudo mostrar el paciente correctamente");
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            logger.error("No se encontró el paciente con el id " + id);
            throw new ResourceNotFoundException("No se encontró el paciente con id=" + id);
        }
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente) throws BadRequestException{
        try {
            if(paciente.getDomicilio() == null){
                throw new BadRequestException("Error al intentar crear un paciente, el domicilio debe ser diligenciado.");
            }
            Paciente pacienteRegistrado = pacienteService.guardarPaciente(paciente);
            logger.info("Se guardó el paciente exitosamente");
            return ResponseEntity.ok(pacienteRegistrado);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new BadRequestException(exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteParaActualizar = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteParaActualizar.isPresent()) {
            logger.info("El paciente se ha actualizado correctamente");
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else {
            logger.info("Error al actualizar el paciente.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteABorrar = pacienteService.buscarPaciente(id);
        if (pacienteABorrar.isPresent()) {
            pacienteService.eliminarPaciente(id);
            logger.info("Se eliminó el paciente con id: " + id + " correctamente.");
            return ResponseEntity.ok("Se eliminó al paciente con ID = " + id);
        } else {
            logger.error("Error al ingresar el id del paciente para eliminarlo");
            throw new ResourceNotFoundException("No se encontró al paciente con id=" + id
                    + " para realizar su eliminación. Verifique que el ID ingresado sea el correcto.");
        }
    }
}
