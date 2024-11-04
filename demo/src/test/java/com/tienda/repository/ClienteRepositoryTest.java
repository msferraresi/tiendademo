package com.tienda.repository;

import com.tienda.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setEmail("juan.perez@example.com");
    }

    @Test
    void testGuardarCliente() {
        Cliente savedCliente = clienteRepository.save(cliente);
        assertNotNull(savedCliente.getId());
        assertEquals("Juan", savedCliente.getNombre());
    }

    @Test
    void testBuscarClientePorId() {
        Cliente savedCliente = clienteRepository.save(cliente);
        Optional<Cliente> foundCliente = clienteRepository.findById(savedCliente.getId());
        assertTrue(foundCliente.isPresent());
        assertEquals(savedCliente.getId(), foundCliente.get().getId());
    }

    @Test
    void testObtenerTodosLosClientes() {
        clienteRepository.save(cliente);
        Cliente otroCliente = new Cliente("Ana", "Gomez", "ana.gomez@example.com");
        clienteRepository.save(otroCliente);

        List<Cliente> clientes = clienteRepository.findAll();
        assertEquals(2, clientes.size());
    }

    @Test
    void testActualizarCliente() {
        Cliente savedCliente = clienteRepository.save(cliente);
        savedCliente.setNombre("Carlos");
        Cliente updatedCliente = clienteRepository.save(savedCliente);

        assertEquals("Carlos", updatedCliente.getNombre());
    }

    @Test
    void testEliminarCliente() {
        Cliente savedCliente = clienteRepository.save(cliente);
        clienteRepository.deleteById(savedCliente.getId());
        Optional<Cliente> deletedCliente = clienteRepository.findById(savedCliente.getId());

        assertFalse(deletedCliente.isPresent());
    }
}
