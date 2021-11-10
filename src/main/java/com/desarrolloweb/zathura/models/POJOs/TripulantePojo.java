package com.desarrolloweb.zathura.models.POJOs;

import com.desarrolloweb.zathura.models.Tripulante;

public class TripulantePojo {
    public Long id;
    public String username;
    public String password;
    public Boolean capitan;
    public Boolean navegante;
    public Boolean comerciante;

    public TripulantePojo() {
        super();
    }

    public TripulantePojo(Long id, String username, String password, Boolean capitan, Boolean navegante,
            Boolean comerciante) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.capitan = capitan;
        this.navegante = navegante;
        this.comerciante = comerciante;
    }

    public Tripulante tripulantePojoToTripulante() {
        Tripulante tripulante = new Tripulante();
        tripulante.setId(id);
        tripulante.setUsername(username);
        tripulante.setPassword(password);
        tripulante.setCapitan(capitan);
        tripulante.setNavegante(navegante);
        tripulante.setComerciante(comerciante);
        return tripulante;
    }
}