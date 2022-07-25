package com.dh.RESTREPO_VIVIANA_INTEGRADOR.controller;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Odontologo;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.BadRequestException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.exceptions.ResourceNotFoundException;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.services.OdontologoServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoServices odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> traerOdontologos() {
        logger.info("Se listaron los odontólogos satisfactoriamente");
        return ResponseEntity.ok(odontologoService.listarOdontologo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> traerOdontologoPorId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            logger.info("Se pudo mostrar el odontólogo correctamente");
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else{
            logger.error("No se encontró el odontólogo con el id " + id);
            throw new ResourceNotFoundException("No se encontró el odontólogo con id=" + id);
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarNuevoOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        try{
            Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologo);
            logger.info("Se guardó el odontólogo exitosamente");
            return ResponseEntity.ok(odontologoRegistrado);
        }catch (Exception exception){
            logger.error(exception.getMessage());
            throw new BadRequestException(exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoParaActualizar= odontologoService.buscarOdontologo(odontologo.getId());
        if(odontologoParaActualizar.isPresent()){
            logger.info("El odontólogo se ha actualizado correctamente");
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        }
        else{
            logger.info("Error al actualizar el odontólogo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoABorrar= odontologoService.buscarOdontologo(id);
        if (odontologoABorrar.isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.info("Se eliminó el odontólogo con id: " + id + " correctamente.");
            return ResponseEntity.ok("Se eliminó al odontólogo con ID=" + id);
        }
        else{
            logger.error("Error al ingresar el id del odontólogo para eliminarlo");
            throw new ResourceNotFoundException("No se encontró al odontólogo con " +
                    "id=" + id + " para realizar su eliminación. Verifique que el ID ingresado sea el correcto.");
        }
    }
}
