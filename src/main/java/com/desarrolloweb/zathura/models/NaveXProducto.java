package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

public class NaveXProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Double cantidad;

    private Double totalCredito;

    @ManyToOne
    Nave nave;
    
    @ManyToOne
    Producto producto;

    public NaveXProducto() {
    }

    public NaveXProducto(Double cantidad, Double totalCredito, Nave nave, Producto producto) {
        this.cantidad = cantidad;
        this.totalCredito = totalCredito;
        this.nave = nave;
        this.producto = producto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotalCredito() {
        return this.totalCredito;
    }

    public void setTotalCredito(Double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public Nave getNave() {
        return this.nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public NaveXProducto id(Long id) {
        setId(id);
        return this;
    }

    public NaveXProducto cantidad(Double cantidad) {
        setCantidad(cantidad);
        return this;
    }

    public NaveXProducto totalCredito(Double totalCredito) {
        setTotalCredito(totalCredito);
        return this;
    }

    public NaveXProducto nave(Nave nave) {
        setNave(nave);
        return this;
    }

    public NaveXProducto producto(Producto producto) {
        setProducto(producto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NaveXProducto)) {
            return false;
        }
        NaveXProducto naveXProducto = (NaveXProducto) o;
        return Objects.equals(id, naveXProducto.id) && Objects.equals(cantidad, naveXProducto.cantidad) && Objects.equals(totalCredito, naveXProducto.totalCredito) && Objects.equals(nave, naveXProducto.nave) && Objects.equals(producto, naveXProducto.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidad, totalCredito, nave, producto);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", cantidad='" + getCantidad() + "'" +
            ", totalCredito='" + getTotalCredito() + "'" +
            ", nave='" + getNave() + "'" +
            ", producto='" + getProducto() + "'" +
            "}";
    }

}
