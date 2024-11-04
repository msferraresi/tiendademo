package com.tienda.repository;

import com.tienda.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente save(Cliente cliente);

    List<Cliente> findAll();

    Optional<Cliente> findById(Long id);

    void delete(Cliente cliente);
    // Puedes agregar métodos de consulta personalizados aquí si es necesario
}
