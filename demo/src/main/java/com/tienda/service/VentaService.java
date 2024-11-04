package com.tienda.service;

import com.tienda.dto.VentaDTO;
import com.tienda.model.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta crearVenta(Venta venta);
    List<Venta> obtenerTodasLasVentas();
    Optional<Venta> obtenerVentaPorId(Long id);
    List<Venta> obtenerVentasPorClienteId(Long clienteId);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);

    VentaDTO createVenta(VentaDTO ventaDTO);

    VentaDTO getVentaById(Long id);

    List<VentaDTO> getAllVentas();

    VentaDTO updateVenta(Long id, VentaDTO ventaDTO);

    void deleteVenta(Long id);
}
