package com.tienda.repository;

import com.tienda.model.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    private Venta venta;

    @BeforeEach
    void setUp() {
        venta = new Venta();
        venta.setFecha(LocalDate.now());
        venta.setMonto(100.0);
    }

    @Test
    void testGuardarVenta() {
        Venta savedVenta = ventaRepository.save(venta);
        assertNotNull(savedVenta.getId());
        assertEquals(100.0, savedVenta.getMonto());
    }

    @Test
    void testBuscarVentaPorId() {
        Venta savedVenta = ventaRepository.save(venta);
        Optional<Venta> foundVenta = ventaRepository.findById(savedVenta.getId());
        assertTrue(foundVenta.isPresent());
        assertEquals(savedVenta.getId(), foundVenta.get().getId());
    }

    @Test
    void testObtenerTodasLasVentas() {
        ventaRepository.save(venta);
        Venta otraVenta = new Venta();
        otraVenta.setFecha(LocalDate.now());
        otraVenta.setMonto(200.0);
        ventaRepository.save(otraVenta);

        List<Venta> ventas = ventaRepository.findAll();
        assertEquals(2, ventas.size());
    }

    @Test
    void testActualizarVenta() {
        Venta savedVenta = ventaRepository.save(venta);
        savedVenta.setMonto(150.0);
        Venta updatedVenta = ventaRepository.save(savedVenta);

        assertEquals(150.0, updatedVenta.getMonto());
    }

    @Test
    void testEliminarVenta() {
        Venta savedVenta = ventaRepository.save(venta);
        ventaRepository.deleteById(savedVenta.getId());
        Optional<Venta> deletedVenta = ventaRepository.findById(savedVenta.getId());

        assertFalse(deletedVenta.isPresent());
    }
}
