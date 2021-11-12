package com.desarrolloweb.zathura.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Tripulante implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private Boolean capitan;

    private Boolean navegante;

    private Boolean comerciante;

    @ManyToOne
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    //@JsonManagedReference(value= "nave_tripulante")
    private Nave nave;

    public Tripulante() {
    }

    public Tripulante(String username, String password, Boolean capitan, Boolean navegante, Boolean comerciante, Nave nave) {
        this.username = username;
        this.password = password;
        this.capitan = capitan;
        this.navegante = navegante;
        this.comerciante = comerciante;
        this.nave = nave;
    }

    public Tripulante(Long id, String username, String password, Boolean capitan, Boolean navegante, Boolean comerciante) {
        this.username = username;
        this.password = password;
        this.capitan = capitan;
        this.navegante = navegante;
        this.comerciante = comerciante;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isCapitan() {
        return this.capitan;
    }

    public Boolean getCapitan() {
        return this.capitan;
    }

    public void setCapitan(Boolean capitan) {
        this.capitan = capitan;
    }

    public Boolean isNavegante() {
        return this.navegante;
    }

    public Boolean getNavegante() {
        return this.navegante;
    }

    public void setNavegante(Boolean navegante) {
        this.navegante = navegante;
    }

    public Boolean isComerciante() {
        return this.comerciante;
    }

    public Boolean getComerciante() {
        return this.comerciante;
    }

    public void setComerciante(Boolean comerciante) {
        this.comerciante = comerciante;
    }

    public Nave getNave() {
        return this.nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tripulante)) {
            return false;
        }
        Tripulante tripulante = (Tripulante) o;
        return Objects.equals(id, tripulante.id) && Objects.equals(username, tripulante.username) && Objects.equals(password, tripulante.password) && Objects.equals(capitan, tripulante.capitan) && Objects.equals(navegante, tripulante.navegante) && Objects.equals(comerciante, tripulante.comerciante) && Objects.equals(nave, tripulante.nave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, capitan, navegante, comerciante, nave);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", capitan='" + isCapitan() + "'" +
            ", navegante='" + isNavegante() + "'" +
            ", comerciante='" + isComerciante() + "'" +
            ", nave='" + getNave() + "'" +
            "}";
    }

}
