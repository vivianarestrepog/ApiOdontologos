package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Domicilio;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Paciente;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceImplTest {

    @Autowired
    PacienteServices pacienteServices;

    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Domicilio domicilio = new Domicilio("Esmeraldal", 27, "Envigado", "ANT");
        Paciente pacienteParaAgregar = new Paciente("Restrepo", "Viviana", "vivianarestrepo@hotmail.com", 432, LocalDate.now(), domicilio);
        Paciente respuesta = pacienteServices.guardarPaciente(pacienteParaAgregar);
        assertNotNull(respuesta);
    }

    @Test
    @Order(2)
    public void buscarPacienteTest() {
        long pacienteIdBuscado = 1L;
        Optional<Paciente> pacienteBuscado = pacienteServices.buscarPaciente(pacienteIdBuscado);
        assertTrue(pacienteBuscado.isPresent());
    }

    @Test
    @Order(1)
    public void listarPacientesTest() {
        List<Paciente> listaPacientes = pacienteServices.listarPacientes();
        assertTrue(listaPacientes.size() == 1);
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest() {
        Optional<Paciente> pacienteBuscado = pacienteServices.buscarPaciente(1L);
        if (pacienteBuscado.isPresent()) {
            pacienteBuscado.get().setNombre("Paula");
        }
        Paciente respuesta = pacienteServices.actualizarPaciente(pacienteBuscado.get());
        assertEquals("Paula", respuesta.getNombre());
    }

    @Test
    @Order(5)
    public void eliminarPacienteTest() {
        Long idBuscadoABorrar = 1L;
        pacienteServices.eliminarPaciente(idBuscadoABorrar);
        Optional<Paciente> pacienteBuscado = pacienteServices.buscarPaciente(idBuscadoABorrar);
        assertTrue(pacienteBuscado.isEmpty());
    }
}