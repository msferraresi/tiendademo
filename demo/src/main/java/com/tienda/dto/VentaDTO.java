package com.tienda.dto;

import java.util.Date;

public class VentaDTO {

    private Long id;
    private Long clienteId;
    private Date fecha;
    private Double total;

    public VentaDTO() {}

    public VentaDTO(Long id, Long clienteId, Date fecha, Double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
