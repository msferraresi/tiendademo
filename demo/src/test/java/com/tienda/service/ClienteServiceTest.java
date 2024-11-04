package com.tienda.service;

import com.tienda.dto.ClienteDTO;
import com.tienda.model.Cliente;
import com.tienda.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setEmail("juan.perez@example.com");
    }

    @Test
    void testGuardarCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        Cliente result = clienteService.guardarCliente(clienteDTO);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testObtenerClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.obtenerClientePorId(1L);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTodosLosClientes() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));

        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();

        assertEquals(1, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testActualizarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        cliente.setNombre("Carlos");
        Cliente result = clienteService.actualizarCliente(1L, new ClienteDTO(cliente));

        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testEliminarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.eliminarCliente(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
