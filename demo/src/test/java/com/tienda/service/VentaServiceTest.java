package com.tienda.service;

import com.tienda.dto.VentaDTO;
import com.tienda.model.Venta;
import com.tienda.repository.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaService ventaService;

    private Venta venta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        venta = new Venta();
        venta.setId(1L);
        venta.setFecha(LocalDate.now());
        venta.setMonto(100.0);
    }

    @Test
    void testGuardarVenta() {
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        VentaDTO ventaDTO = new VentaDTO(venta);
        Venta result = ventaService.guardarVenta(ventaDTO);

        assertNotNull(result);
        assertEquals(100.0, result.getMonto());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void testObtenerVentaPorId() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        Venta result = ventaService.obtenerVentaPorId(1L);

        assertNotNull(result);
        assertEquals(100.0, result.getMonto());
        verify(ventaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodasLasVentas() {
        when(ventaRepository.findAll()).thenReturn(Arrays.asList(venta));

        List<Venta> ventas = ventaService.obtenerTodasLasVentas();

        assertEquals(1, ventas.size());
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void testActualizarVenta() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        venta.setMonto(150.0);
        Venta result = ventaService.actualizarVenta(1L, new VentaDTO(venta));

        assertNotNull(result);
        assertEquals(150.0, result.getMonto());
        verify(ventaRepository, times(1)).save(venta);
    }

    @Test
    void testEliminarVenta() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        doNothing().when(ventaRepository).deleteById(1L);

        ventaService.eliminarVenta(1L);

        verify(ventaRepository, times(1)).deleteById(1L);
    }
}
