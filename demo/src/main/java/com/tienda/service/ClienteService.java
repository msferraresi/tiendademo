package com.tienda.service;

import com.tienda.dto.ClienteDTO;
import com.tienda.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> obtenerTodosLosClientes();
    Optional<Cliente> obtenerClientePorId(Long id);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);

    Cliente createCliente(ClienteDTO clienteDTO);

    ClienteDTO getClienteById(Long id);

    List<ClienteDTO> getAllClientes();

    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);

    void deleteCliente(Long id);
}
