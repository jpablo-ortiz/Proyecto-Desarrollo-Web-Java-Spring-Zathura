package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class NaveXProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Double stock;

    private Double totalCredito;

    private Double totalVolumen;

    @ManyToOne
    @JsonManagedReference
    private Nave nave;
    
    @ManyToOne
    @JsonManagedReference
    private Producto producto;

    public NaveXProducto() {
    }

    public NaveXProducto(Double stock, Double totalCredito, Double totalVolumen, Nave nave, Producto producto) {
        this.stock = stock;
        this.totalCredito = totalCredito;
        this.totalVolumen = totalVolumen;
        this.nave = nave;
        this.producto = producto;
    }
    
    public NaveXProducto(Long id ,Double stock, Double totalCredito, Double totalVolumen) {
        this.id = id;        
        this.stock = stock;
        this.totalCredito = totalCredito;
        this.totalVolumen = totalVolumen;
      
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getStock() {
        return this.stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getTotalCredito() {
        return this.totalCredito;
    }

    public void setTotalCredito(Double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public Double getTotalVolumen() {
        return this.totalVolumen;
    }

    public void setTotalVolumen(Double totalVolumen) {
        this.totalVolumen = totalVolumen;
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

    public NaveXProducto stock(Double stock) {
        setStock(stock);
        return this;
    }

    public NaveXProducto totalCredito(Double totalCredito) {
        setTotalCredito(totalCredito);
        return this;
    }

    public NaveXProducto totalVolumen(Double totalVolumen) {
        setTotalVolumen(totalVolumen);
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
        return Objects.equals(id, naveXProducto.id) && Objects.equals(stock, naveXProducto.stock) && Objects.equals(totalCredito, naveXProducto.totalCredito) && Objects.equals(totalVolumen, naveXProducto.totalVolumen) && Objects.equals(nave, naveXProducto.nave) && Objects.equals(producto, naveXProducto.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, totalCredito, totalVolumen, nave, producto);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", stock='" + getStock() + "'" +
            ", totalCredito='" + getTotalCredito() + "'" +
            ", totalVolumen='" + getTotalVolumen() + "'" +
            ", nave='" + getNave() + "'" +
            ", producto='" + getProducto() + "'" +
            "}";
    }
    
}
