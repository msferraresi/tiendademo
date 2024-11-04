package com.tienda.controller;

import com.tienda.dto.ClienteDTO;
import com.tienda.exceptions.ResourceNotFoundException;
import com.tienda.model.Cliente;
import com.tienda.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "juan.perez@example.com");
        when(clienteService.crearCliente(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.crearCliente(cliente);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testObtenerTodosLosClientes() {
        List<Cliente> clientes = Arrays.asList(
                new Cliente(1L, "Juan", "Perez", "juan.perez@example.com"),
                new Cliente(2L, "Ana", "Gomez", "ana.gomez@example.com")
        );
        when(clienteService.obtenerTodosLosClientes()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clienteController.obtenerTodosLosClientes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    void testObtenerClientePorId() {
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "juan.perez@example.com");
        when(clienteService.obtenerClientePorId(1L)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testObtenerClientePorId_NotFound() {
        when(clienteService.obtenerClientePorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testActualizarCliente() {
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "juan.perez@example.com");
        when(clienteService.actualizarCliente(1L, cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.actualizarCliente(1L, cliente);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testEliminarCliente() {
        doNothing().when(clienteService).eliminarCliente(1L);

        ResponseEntity<Void> response = clienteController.eliminarCliente(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clienteService, times(1)).eliminarCliente(1L);
    }
}
