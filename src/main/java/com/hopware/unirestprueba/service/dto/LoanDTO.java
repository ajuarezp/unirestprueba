package com.hopware.unirestprueba.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Loan entity.
 */
public class LoanDTO implements Serializable {

    private Long id;

    private String nombre;

    private Double monto;

    private String banco;

    private LocalDate fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanDTO loanDTO = (LoanDTO) o;

        if ( ! Objects.equals(id, loanDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", monto='" + monto + "'" +
            ", banco='" + banco + "'" +
            ", fecha='" + fecha + "'" +
            '}';
    }
}
