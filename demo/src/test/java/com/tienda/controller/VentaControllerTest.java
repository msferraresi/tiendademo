package com.tienda.controller;

import com.tienda.model.Venta;
import com.tienda.service.VentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VentaControllerTest {

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private VentaController ventaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearVenta() {
        Venta venta = new Venta(1L, LocalDate.now(), 100.0);
        when(ventaService.crearVenta(venta)).thenReturn(venta);

        ResponseEntity<Venta> response = ventaController.crearVenta(venta);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(venta, response.getBody());
    }

    @Test
    void testObtenerTodasLasVentas() {
        List<Venta> ventas = Arrays.asList(
                new Venta(1L, LocalDate.now(), 100.0),
                new Venta(2L, LocalDate.now(), 200.0)
        );
        when(ventaService.obtenerTodasLasVentas()).thenReturn(ventas);

        ResponseEntity<List<Venta>> response = ventaController.obtenerTodasLasVentas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ventas, response.getBody());
    }

    @Test
    void testObtenerVentaPorId() {
        Venta venta = new Venta(1L, LocalDate.now(), 100.0);
        when(ventaService.obtenerVentaPorId(1L)).thenReturn(Optional.of(venta));

        ResponseEntity<Venta> response = ventaController.obtenerVentaPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(venta, response.getBody());
    }

    @Test
    void testObtenerVentaPorId_NotFound() {
        when(ventaService.obtenerVentaPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Venta> response = ventaController.obtenerVentaPorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testActualizarVenta() {
        Venta venta = new Venta(1L, LocalDate.now(), 150.0);
        when(ventaService.actualizarVenta(1L, venta)).thenReturn(venta);

        ResponseEntity<Venta> response = ventaController.actualizarVenta(1L, venta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(venta, response.getBody());
    }

    @Test
    void testEliminarVenta() {
        doNothing().when(ventaService).eliminarVenta(1L);

        ResponseEntity<Void> response = ventaController.eliminarVenta(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ventaService, times(1)).eliminarVenta(1L);
    }
}
