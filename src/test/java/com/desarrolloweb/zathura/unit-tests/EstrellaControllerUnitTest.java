package com.desarrolloweb.zathura.unit.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.desarrolloweb.zathura.models.Estrella;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.repositories.EstrellaRepository;
import com.desarrolloweb.zathura.repositories.ModeloNaveRepository;
import com.desarrolloweb.zathura.repositories.NaveRepository;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;
import com.desarrolloweb.zathura.repositories.TripulanteRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("unit-test")
@DirtiesContext
@AutoConfigureMockMvc
public class EstrellaControllerUnitTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private EstrellaRepository estrellaRepository;

    @MockBean
    private PlanetaRepository planetaRepository;

    @MockBean
    private ModeloNaveRepository modeloNaveRepository;

    @MockBean
    private NaveRepository naveRepository;

    @MockBean
    private TripulanteRepository tripulanteRepository;

    private static boolean inicializado;

    @BeforeAll
    public static void beforeAll() {
        inicializado = false;
    }

    @BeforeEach
    public void init() {
        if (!inicializado) {
            // -------------------- Estrellas --------------------
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

            Mockito.when(estrellaRepository.findById(1l)).thenReturn(Optional.of(estrella1));
            Mockito.when(estrellaRepository.findById(2l)).thenReturn(Optional.of(estrella2));
            Mockito.when(estrellaRepository.findById(3l)).thenReturn(Optional.of(estrella3));
            Mockito.when(estrellaRepository.findById(4l)).thenReturn(Optional.of(estrella4));
            Mockito.when(estrellaRepository.findById(5l)).thenReturn(Optional.of(estrella5));
            Mockito.when(estrellaRepository.findById(6l)).thenReturn(Optional.of(estrella6));
            Mockito.when(estrellaRepository.findById(7l)).thenReturn(Optional.of(estrella7));
            Mockito.when(estrellaRepository.findById(8l)).thenReturn(Optional.of(estrella8));
            Mockito.when(estrellaRepository.findById(9l)).thenReturn(Optional.of(estrella9));
            Mockito.when(estrellaRepository.findById(10l)).thenReturn(Optional.of(estrella10));

            Mockito.when(estrellaRepository.findAll()).thenReturn(estrellas);

            // -------------------- Planetas --------------------
            Planeta planeta1 = new Planeta("planeta1", true, estrella1);
            Mockito.when(planetaRepository.findById(11l)).thenReturn(Optional.of(planeta1));

            // -------------------- Modelo Nave --------------------
            ModeloNave modeloNave1 = new ModeloNave("modeloNave1", Double.valueOf(100), Double.valueOf(100),
                    Double.valueOf(100));
            Mockito.when(modeloNaveRepository.findById(12l)).thenReturn(Optional.of(modeloNave1));

            // -------------------- Nave --------------------
            Nave nave1 = new Nave("nave1", Double.valueOf(100), Double.valueOf(100), Double.valueOf(100), planeta1,
                    modeloNave1);
            Mockito.when(naveRepository.findById(13l)).thenReturn(Optional.of(nave1));

            // -------------------- Tripulante --------------------
            Tripulante tripulante = new Tripulante("usuario1", "1234", true, false, false, nave1);
            Mockito.when(tripulanteRepository.findById(14l)).thenReturn(Optional.of(tripulante));

            inicializado = true;
        }
    }

    @Test
    void buscarEstrella() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/estrella/{id}", 1);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("1", content);
    }

    @Test
    void buscarEstrellas() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/estrella");
        MvcResult result = mvc.perform(request).andReturn();
        int content = result.getResponse().getContentLength();
        assertEquals(10, content);
    }

    @Test
    void verificarViaje() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/estrella/{id}/verificar-viaje/{id}/tripulante/{id}", 1, 2, 14);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);
    }

    @Test
    void viajar() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/estrella/{id}/viajar/{id}/tripulante/{id}",
                1, 2, 14);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("true", content);
    }

    @Test
    void obtener10EstrellasCercanas() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/estrella/10nearest/{id}", 1);
        MvcResult result = mvc.perform(request).andReturn();
        int content = result.getResponse().getContentLength();
        assertEquals(9, content);
    }

}
