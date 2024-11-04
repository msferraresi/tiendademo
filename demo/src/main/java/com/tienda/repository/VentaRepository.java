package com.tienda.repository;

import com.tienda.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);

    Venta save(Venta venta);

    List<Venta> findAll();

    Optional<Venta> findById(Long id);

    void delete(Venta venta);
}
