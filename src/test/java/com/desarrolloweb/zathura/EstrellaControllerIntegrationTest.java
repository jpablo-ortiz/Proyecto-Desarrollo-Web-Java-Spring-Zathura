package com.desarrolloweb.zathura;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.zathura.controllers.RutaController;
import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Ruta;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EstrellaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    private EstrellaRepository estrellaRepository;

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private RutaController rutaController;

    @BeforeEach
    void init() {
        List<Estrella> estrellas = new ArrayList<Estrella>();
        Estrella estrella1 = new Estrella("estrella1", Double.valueOf(2), Double.valueOf(4), Double.valueOf(5), true);
        Estrella estrella2 = new Estrella("estrella2", Double.valueOf(23), Double.valueOf(47), Double.valueOf(52),
                true);
        Estrella estrella3 = new Estrella("estrella3", Double.valueOf(42), Double.valueOf(49), Double.valueOf(56),
                true);
        Estrella estrella4 = new Estrella("estrella4", Double.valueOf(67), Double.valueOf(42), Double.valueOf(59),
                true);

        estrellaRepository.save(estrella1); // id1
        estrellaRepository.save(estrella2); // id2
        estrellaRepository.save(estrella3); // id3
        estrellaRepository.save(estrella4); // id4

        estrellas.add(estrella1);
        estrellas.add(estrella2);
        estrellas.add(estrella3);
        estrellas.add(estrella4);
        Planeta planeta1 = new Planeta("planeta1", true, estrella1);

        planetaRepository.save(planeta1); // id5

        // Crear Rutas de todas las estrellas con todas las estrellas
        for (int i = 0; i < estrellas.size(); i++) {
            for (int j = i + 1; j < estrellas.size(); j++) {
                Ruta ruta = new Ruta(estrellas.get(i), estrellas.get(j));
                rutaController.crearRuta(ruta);
            }
        }

    }

    @Test
    void buscarEstrella() {
        Estrella estrella = rest.getForObject("http://localhost:" + port + "/estrella/1", Estrella.class);
        assertEquals(1, estrella.getId());
    }
}
