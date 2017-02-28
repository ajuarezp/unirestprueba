package com.hopware.unirestprueba.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Loan.
 */
@Entity
@Table(name = "loan")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "banco")
    private String banco;

    @Column(name = "fecha")
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

    public Loan nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMonto() {
        return monto;
    }

    public Loan monto(Double monto) {
        this.monto = monto;
        return this;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getBanco() {
        return banco;
    }

    public Loan banco(String banco) {
        this.banco = banco;
        return this;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Loan fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
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
        Loan loan = (Loan) o;
        if (loan.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Loan{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", monto='" + monto + "'" +
            ", banco='" + banco + "'" +
            ", fecha='" + fecha + "'" +
            '}';
    }
}
