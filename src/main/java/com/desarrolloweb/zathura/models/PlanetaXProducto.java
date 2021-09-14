/*package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import javax.persistence.Id;

@Entity
public class PlanetaXProducto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Double precioVenta;

    private Double precioCompra;

    private Double factorDemanda;

    private Double factorOferta;

    private Integer stock;

    @ManyToOne
    private Planeta planeta;

    @ManyToOne
    private Producto producto;

    public PlanetaXProducto() {
    }

    public PlanetaXProducto(Double precioVenta, Double precioCompra, Double factorDemanda, Double factorOferta, Integer stock, Planeta planeta, Producto producto) {
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
        this.stock = stock;
        this.planeta = planeta;
        this.producto = producto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioCompra() {
        return this.precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getFactorDemanda() {
        return this.factorDemanda;
    }

    public void setFactorDemanda(Double factorDemanda) {
        this.factorDemanda = factorDemanda;
    }

    public Double getFactorOferta() {
        return this.factorOferta;
    }

    public void setFactorOferta(Double factorOferta) {
        this.factorOferta = factorOferta;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Planeta getPlaneta() {
        return this.planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public PlanetaXProducto id(Long id) {
        setId(id);
        return this;
    }

    public PlanetaXProducto precioVenta(Double precioVenta) {
        setPrecioVenta(precioVenta);
        return this;
    }

    public PlanetaXProducto precioCompra(Double precioCompra) {
        setPrecioCompra(precioCompra);
        return this;
    }

    public PlanetaXProducto factorDemanda(Double factorDemanda) {
        setFactorDemanda(factorDemanda);
        return this;
    }

    public PlanetaXProducto factorOferta(Double factorOferta) {
        setFactorOferta(factorOferta);
        return this;
    }

    public PlanetaXProducto stock(Integer stock) {
        setStock(stock);
        return this;
    }

    public PlanetaXProducto planeta(Planeta planeta) {
        setPlaneta(planeta);
        return this;
    }

    public PlanetaXProducto producto(Producto producto) {
        setProducto(producto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlanetaXProducto)) {
            return false;
        }
        PlanetaXProducto planetaXProducto = (PlanetaXProducto) o;
        return Objects.equals(id, planetaXProducto.id) && Objects.equals(precioVenta, planetaXProducto.precioVenta) && Objects.equals(precioCompra, planetaXProducto.precioCompra) && Objects.equals(factorDemanda, planetaXProducto.factorDemanda) && Objects.equals(factorOferta, planetaXProducto.factorOferta) && Objects.equals(stock, planetaXProducto.stock) && Objects.equals(planeta, planetaXProducto.planeta) && Objects.equals(producto, planetaXProducto.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precioVenta, precioCompra, factorDemanda, factorOferta, stock, planeta, producto);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", precioVenta='" + getPrecioVenta() + "'" +
            ", precioCompra='" + getPrecioCompra() + "'" +
            ", factorDemanda='" + getFactorDemanda() + "'" +
            ", factorOferta='" + getFactorOferta() + "'" +
            ", stock='" + getStock() + "'" +
            ", planeta='" + getPlaneta() + "'" +
            ", producto='" + getProducto() + "'" +
            "}";
    }

}
*/