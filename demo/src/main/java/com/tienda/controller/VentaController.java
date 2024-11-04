package com.tienda.controller;

import com.tienda.dto.VentaDTO;
import com.tienda.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDTO> createVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO createdVenta = ventaService.createVenta(ventaDTO);
        return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> getVentaById(@PathVariable Long id) {
        VentaDTO ventaDTO = ventaService.getVentaById(id);
        return new ResponseEntity<>(ventaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        List<VentaDTO> ventas = ventaService.getAllVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> updateVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        VentaDTO updatedVenta = ventaService.updateVenta(id, ventaDTO);
        return new ResponseEntity<>(updatedVenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
