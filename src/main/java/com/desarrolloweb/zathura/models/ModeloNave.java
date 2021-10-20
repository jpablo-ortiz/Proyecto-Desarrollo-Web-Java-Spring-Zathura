package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ModeloNave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nombreModelo;

    private Double cargaMax;

    private Double velocidadMax;

    @OneToMany(mappedBy = "modeloNave")
    @JsonBackReference
    private List<Nave> naves = new ArrayList<>();

    public ModeloNave() {
    }

    public ModeloNave(Long id, String nombreModelo, Double cargaMax, Double velocidadMax) {
        this.id = id;
        this.nombreModelo = nombreModelo;
        this.cargaMax = cargaMax;
        this.velocidadMax = velocidadMax;
    }

    public ModeloNave(Long id, String nombreModelo, Double cargaMax, Double velocidadMax, List<Nave> naves) {
        this.id = id;
        this.nombreModelo = nombreModelo;
        this.cargaMax = cargaMax;
        this.velocidadMax = velocidadMax;
        this.naves = naves;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreModelo() {
        return this.nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Double getCargaMax() {
        return this.cargaMax;
    }

    public void setCargaMax(Double cargaMax) {
        this.cargaMax = cargaMax;
    }

    public Double getVelocidadMax() {
        return this.velocidadMax;
    }

    public void setVelocidadMax(Double velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public List<Nave> getNaves() {
        return this.naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public ModeloNave id(Long id) {
        setId(id);
        return this;
    }

    public ModeloNave nombreModelo(String nombreModelo) {
        setNombreModelo(nombreModelo);
        return this;
    }

    public ModeloNave cargaMax(Double cargaMax) {
        setCargaMax(cargaMax);
        return this;
    }

    public ModeloNave velocidadMax(Double velocidadMax) {
        setVelocidadMax(velocidadMax);
        return this;
    }

    public ModeloNave naves(List<Nave> naves) {
        setNaves(naves);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModeloNave)) {
            return false;
        }
        ModeloNave modeloNave = (ModeloNave) o;
        return Objects.equals(id, modeloNave.id) && Objects.equals(nombreModelo, modeloNave.nombreModelo) && Objects.equals(cargaMax, modeloNave.cargaMax) && Objects.equals(velocidadMax, modeloNave.velocidadMax) && Objects.equals(naves, modeloNave.naves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreModelo, cargaMax, velocidadMax, naves);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombreModelo='" + getNombreModelo() + "'" +
            ", cargaMax='" + getCargaMax() + "'" +
            ", velocidadMax='" + getVelocidadMax() + "'" +
            ", naves='" + getNaves() + "'" +
            "}";
    }
    
}
