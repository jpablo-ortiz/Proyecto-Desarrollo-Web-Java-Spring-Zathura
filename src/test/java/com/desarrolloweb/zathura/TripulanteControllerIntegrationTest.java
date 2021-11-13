package com.desarrolloweb.zathura;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.zathura.controllers.RutaController;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.repositories.ModeloNaveRepository;
import com.desarrolloweb.zathura.repositories.NaveRepository;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;
import com.desarrolloweb.zathura.repositories.TripulanteRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
@DirtiesContext
public class TripulanteControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    private EstrellaRepository estrellaRepository;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private RutaController rutaController;

    @Autowired
    private ModeloNaveRepository modeloNaveRepository;

    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private TripulanteRepository tripulanteRepository;

        private static boolean inicializado;

    private static boolean finalizado;

    @BeforeAll
    public static void beforeAll() {
        inicializado = false;
        finalizado = false;
    }

    @AfterAll
    public static void afterAll() {
        finalizado = true;
    }

    @AfterEach
    public void end() {
        if (finalizado) {
            this.tripulanteRepository.deleteAll();
            this.planetaRepository.deleteAll();
            this.naveRepository.deleteAll();
            this.modeloNaveRepository.deleteAll();
            this.rutaController.deleteAll();
            this.estrellaRepository.deleteAll();
        }
    }

    @BeforeEach
    void init() {
        if (!inicializado) {
            List<Estrella> estrellas = new ArrayList<Estrella>();
            Estrella estrella1 = new Estrella("estrella1", Double.valueOf(2), Double.valueOf(4), Double.valueOf(5),
                    true);
            Estrella estrella2 = new Estrella("estrella2", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                    true);
            Estrella estrella3 = new Estrella("estrella3", Double.valueOf(42), Double.valueOf(49), Double.valueOf(56),
                    true);
            Estrella estrella4 = new Estrella("estrella4", Double.valueOf(67), Double.valueOf(42), Double.valueOf(59),
                    true);
            Estrella estrella5 = new Estrella("estrella5", Double.valueOf(12), Double.valueOf(34), Double.valueOf(45),
                    true);
            Estrella estrella6 = new Estrella("estrella6", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                    true);
            Estrella estrella7 = new Estrella("estrella7", Double.valueOf(42), Double.valueOf(49), Double.valueOf(56),
                    true);
            Estrella estrella8 = new Estrella("estrella8", Double.valueOf(67), Double.valueOf(42), Double.valueOf(59),
                    true);
            Estrella estrella9 = new Estrella("estrella9", Double.valueOf(12), Double.valueOf(34), Double.valueOf(45),
                    true);
            Estrella estrella10 = new Estrella("estrella10", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                    true);

            estrellaRepository.save(estrella1); // id1
            estrellaRepository.save(estrella2); // id2
            estrellaRepository.save(estrella3); // id3
            estrellaRepository.save(estrella4); // id4
            estrellaRepository.save(estrella5); // id5
            estrellaRepository.save(estrella6); // id6
            estrellaRepository.save(estrella7); // id7
            estrellaRepository.save(estrella8); // id8
            estrellaRepository.save(estrella9); // id9
            estrellaRepository.save(estrella10); // id10

            estrellas.add(estrella1);
            estrellas.add(estrella2);
            estrellas.add(estrella3);
            estrellas.add(estrella4);
            estrellas.add(estrella5);
            estrellas.add(estrella6);
            estrellas.add(estrella7);
            estrellas.add(estrella8);
            estrellas.add(estrella9);
            estrellas.add(estrella10);
            Planeta planeta1 = new Planeta("planeta1", true, estrella1);
            planetaRepository.save(planeta1); // id11
            // Crear modelo nave

            ModeloNave modeloNave1 = new ModeloNave("modeloNave1", Double.valueOf(100), Double.valueOf(100),
                    Double.valueOf(100));
            modeloNaveRepository.save(modeloNave1); // id12
            // crear Nave

            Nave nave1 = new Nave("nave1", Double.valueOf(100), Double.valueOf(100), Double.valueOf(100), planeta1,
                    modeloNave1);
            naveRepository.save(nave1); // id13
            // Crear Tripulantes
            Tripulante tripulante = new Tripulante("usuario1", "1234", true, false, false, nave1);
            tripulanteRepository.save(tripulante); // id14

            // Crear Rutas de todas las estrellas con todas las estrellas
            for (int i = 0; i < estrellas.size(); i++) {
                for (int j = i + 1; j < estrellas.size(); j++) {
                    Ruta ruta = new Ruta(estrellas.get(i), estrellas.get(j));
                    rutaController.crearRuta(ruta);
                }
            }
            inicializado = true;
        }

    }

    @Test
    void buscarTripulante() {
        Tripulante tripulante = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14", Tripulante.class);
        assertEquals(14, tripulante.getId());
    }

    @Test
    void obtenerTripulacionNave() {
        List<Tripulante> tripulantes = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/nave/13", ArrayList.class);
        assertEquals(1, tripulantes.size());
    }

    @Test
    void obtenerTripulacion() {
        List<Tripulante> tripulantes = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante", ArrayList.class);
        assertEquals(1, tripulantes.size());
    }

    @Test
    void obtenerEstrellaActualPorTripulante() {
        Estrella estrella = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/estrella", Estrella.class);
        assertEquals(1, estrella.getId());

    }

    @Test
    void obtenerEstrellaActualPorTripulanteNegativo() {
        Estrella estrella = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/estrella", Estrella.class);
        assertNotEquals(2, estrella.getId());
    }

    @Test
    void obtenerPlanetaActualPorTripulante() {
        Planeta planeta = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/planeta", Planeta.class);
        assertEquals(11, planeta.getId());
    }

    @Test
    void obtenerPlanetaActualPorTripulanteNegativo() {
        Planeta planeta = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/planeta", Planeta.class);
        assertNotEquals(16, planeta.getId());
    }

    @Test
    void obtenerNaveActualPorTripulante() {
        Nave nave = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/nave", Nave.class);
        assertEquals(13, nave.getId());
    }

    @Test
    void obtenerNaveActualPorTripulanteNegativo() {
        Nave nave = rest.withBasicAuth("usuario1", "1234")
                .getForObject("http://localhost:" + port + "/tripulante/14/nave", Nave.class);
        assertNotEquals(11, nave.getId());
    }

    // faltan dos pruebas

}
