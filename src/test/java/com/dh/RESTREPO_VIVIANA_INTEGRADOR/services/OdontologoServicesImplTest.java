package com.dh.RESTREPO_VIVIANA_INTEGRADOR.services;
import com.dh.RESTREPO_VIVIANA_INTEGRADOR.entities.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServicesImplTest {

    @Autowired
    OdontologoServices odontologoServices;


    @Test
    @Order(1)
    public void guardarOdontologoTest() {
        Odontologo odontologoParaAgregar = new Odontologo("Ernesto", "Perez", 123);
        Odontologo respuesta = odontologoServices.guardarOdontologo(odontologoParaAgregar);
        assertNotNull(respuesta);
    }

    @Test
    @Order(2)
    public void buscarOdontologoTest() {
        long odontologoIdBuscado = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoServices.buscarOdontologo(odontologoIdBuscado);
        assertTrue(odontologoBuscado.isPresent());
    }

    @Test
    @Order(3)
    public void listarOdontologosTest() {
        List<Odontologo> listaOdontologos = odontologoServices.listarOdontologo();
        assertTrue(listaOdontologos.size() == 1);
    }

    @Test
    @Order(4)
    public void actualizarOdontolongoTest() {
        Optional<Odontologo> odontologoBuscado = odontologoServices.buscarOdontologo(1L);
        if (odontologoBuscado.isPresent()) {
            odontologoBuscado.get().setNombre("Frailejon");
        }
       Odontologo respuesta = odontologoServices.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("Frailejon", respuesta.getNombre());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest() {
        Long idBuscadoABorrar = 1L;
        odontologoServices.eliminarOdontologo(idBuscadoABorrar);
        Optional<Odontologo> odontologoBuscado = odontologoServices.buscarOdontologo(idBuscadoABorrar);
        assertTrue(odontologoBuscado.isEmpty());
    }
}