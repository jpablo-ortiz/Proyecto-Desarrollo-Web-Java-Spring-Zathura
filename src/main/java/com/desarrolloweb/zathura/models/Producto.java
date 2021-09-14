package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Double costoCredito;

    private Double volumen;

    private Double peso;

    // @OneToMany(mappedBy = "producto")
    // List<PlanetaXProducto> productoXPlanetas;

    @ManyToMany
    @JoinTable(name = "planeta_x_producto", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "planeta_id"))
    private List<Planeta> planetas = new ArrayList<>();

    // @OneToMany(mappedBy = "producto")
    // List<NaveXProducto> productoXNaves;

    @ManyToMany
    @JoinTable(name = "nave_x_producto", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "nave_id"))
    private List<Nave> naves = new ArrayList<>();

    public Producto() {
    }

    public Producto(Long id, String nombre, Double costoCredito, Double volumen, Double peso, List<Planeta> planetas, List<Nave> naves) {
        this.id = id;
        this.nombre = nombre;
        this.costoCredito = costoCredito;
        this.volumen = volumen;
        this.peso = peso;
        this.planetas = planetas;
        this.naves = naves;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCostoCredito() {
        return this.costoCredito;
    }

    public void setCostoCredito(Double costoCredito) {
        this.costoCredito = costoCredito;
    }

    public Double getVolumen() {
        return this.volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public List<Planeta> getPlanetas() {
        return this.planetas;
    }

    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    public List<Nave> getNaves() {
        return this.naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public Producto id(Long id) {
        setId(id);
        return this;
    }

    public Producto nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Producto costoCredito(Double costoCredito) {
        setCostoCredito(costoCredito);
        return this;
    }

    public Producto volumen(Double volumen) {
        setVolumen(volumen);
        return this;
    }

    public Producto peso(Double peso) {
        setPeso(peso);
        return this;
    }

    public Producto planetas(List<Planeta> planetas) {
        setPlanetas(planetas);
        return this;
    }

    public Producto naves(List<Nave> naves) {
        setNaves(naves);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id) && Objects.equals(nombre, producto.nombre) && Objects.equals(costoCredito, producto.costoCredito) && Objects.equals(volumen, producto.volumen) && Objects.equals(peso, producto.peso) && Objects.equals(planetas, producto.planetas) && Objects.equals(naves, producto.naves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, costoCredito, volumen, peso, planetas, naves);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", costoCredito='" + getCostoCredito() + "'" +
            ", volumen='" + getVolumen() + "'" +
            ", peso='" + getPeso() + "'" +
            ", planetas='" + getPlanetas() + "'" +
            ", naves='" + getNaves() + "'" +
            "}";
    }

}
