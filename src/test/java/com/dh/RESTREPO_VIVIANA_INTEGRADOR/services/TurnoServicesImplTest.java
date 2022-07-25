package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Domicilio;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Odontologo;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Paciente;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Turno;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServicesImplTest {

    @Autowired
    TurnoServices turnoServices;

    @Autowired
    PacienteServices pacienteServices;

    @Autowired
    OdontologoServices odontologoServices;


    @Test
    @Order(1)
    public void guardarTurnoTest(){
        Domicilio domicilio = new Domicilio("Esmeraldal", 27, "Envigado", "ANT");
        Paciente paciente = pacienteServices.guardarPaciente(new Paciente("Restrepo", "Viviana", "vivianarestrepo@hotmail.com", 432, LocalDate.now(), domicilio));
        Odontologo odontologo = odontologoServices.guardarOdontologo(new Odontologo("Ernesto", "Perez", 123));

        Turno turnoParaAgregar = new Turno(odontologo, paciente, LocalDate.now());
        Turno respuesta = turnoServices.guardarTurno(turnoParaAgregar);
        assertNotNull(respuesta);
    }

    @Test
    @Order(2)
    public void buscarTurnoTest(){
        long turnoIdBuscado = 1L;
        Optional<Turno> turnoBuscado = turnoServices.buscarTurno(turnoIdBuscado);
        assertTrue(turnoBuscado.isPresent());
    }

    @Test
    @Order(3)
    public void listarTurnosTest(){
        List<Turno> listaTurnos = turnoServices.listarTurnos();
        assertTrue(listaTurnos.size() == 1);
    }

    @Test
    @Order(4)
    public void actualizarTurnosTest(){
        Optional<Turno> turnoBuscado = turnoServices.buscarTurno(1L);
        LocalDate fechaActualizada = LocalDate.of(2022, 06, 01);

        turnoBuscado.ifPresent(turno -> turno.setFecha(fechaActualizada));
        Turno respuesta = turnoServices.actualizarTurno(turnoBuscado.get());
        assertEquals(fechaActualizada, respuesta.getFecha());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        Long idBuscadoABorrar = 1L;
        turnoServices.eliminarTurno(idBuscadoABorrar);

        Optional<Turno> turnoBuscado = turnoServices.buscarTurno(idBuscadoABorrar);
        assertTrue(turnoBuscado.isEmpty());
    }
}