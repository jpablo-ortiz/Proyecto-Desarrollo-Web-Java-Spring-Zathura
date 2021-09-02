package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

@Entity
public class Ruta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Double distancia;

    @ManyToOne
    private Estrella estrellaA;

    @ManyToOne
    private Estrella estrellaB;

    public Ruta() {
    }

    public Ruta(Double distancia, Estrella estrellaA, Estrella estrellaB) {
        this.distancia = distancia;
        this.estrellaA = estrellaA;
        this.estrellaB = estrellaB;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Estrella getEstrellaA() {
        return this.estrellaA;
    }

    public void setEstrellaA(Estrella estrellaA) {
        this.estrellaA = estrellaA;
    }

    public Estrella getEstrellaB() {
        return this.estrellaB;
    }

    public void setEstrellaB(Estrella estrellaB) {
        this.estrellaB = estrellaB;
    }

    public Ruta id(Long id) {
        setId(id);
        return this;
    }

    public Ruta distancia(Double distancia) {
        setDistancia(distancia);
        return this;
    }

    public Ruta estrellaA(Estrella estrellaA) {
        setEstrellaA(estrellaA);
        return this;
    }

    public Ruta estrellaB(Estrella estrellaB) {
        setEstrellaB(estrellaB);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ruta)) {
            return false;
        }
        Ruta ruta = (Ruta) o;
        return Objects.equals(id, ruta.id) && Objects.equals(distancia, ruta.distancia)
                && Objects.equals(estrellaA, ruta.estrellaA) && Objects.equals(estrellaB, ruta.estrellaB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distancia, estrellaA, estrellaB);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", distancia='" + getDistancia() + "'" + ", estrellaA='" + getEstrellaA()
                + "'" + ", estrellaB='" + getEstrellaB() + "'" + "}";
    }

}
