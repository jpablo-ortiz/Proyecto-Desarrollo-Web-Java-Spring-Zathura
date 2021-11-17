package com.desarrolloweb.zathura;

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
import com.desarrolloweb.zathura.repositories.NaveXProductoRepository;
import com.desarrolloweb.zathura.repositories.PlanetaRepository;
import com.desarrolloweb.zathura.repositories.PlanetaXProductoRepository;
import com.desarrolloweb.zathura.repositories.ProductoRepository;
import com.desarrolloweb.zathura.repositories.RutaRepository;
import com.desarrolloweb.zathura.repositories.TripulanteRepository;
import com.jayway.jsonpath.JsonPath;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
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
public class TripulanteControllerUnitTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private EstrellaRepository estrellaRepository;

    @MockBean
    private ModeloNaveRepository modeloNaveRepository;

    @MockBean
    private NaveRepository naveRepository;

    @MockBean
    private PlanetaRepository planetaRepository;

    @MockBean
    private TripulanteRepository tripulanteRepository;

    @MockBean
    private RutaRepository rutaRepository;

    @MockBean
    private ProductoRepository productoRepository;

    @MockBean
    private NaveXProductoRepository naveXProductoRepository;

    @MockBean
    private PlanetaXProductoRepository planetaXProductoRepository;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @BeforeEach
    public void init() {
        // -------------------- Tripulantes --------------------
        Estrella estrella1 = new Estrella("estrella1", Double.valueOf(2), Double.valueOf(4), Double.valueOf(5), true);
        Mockito.when(estrellaRepository.findById(1l)).thenReturn(Optional.of(estrella1));

        Planeta planeta1 = new Planeta("planeta1", true, estrella1);
        Mockito.when(planetaRepository.findById(2l)).thenReturn(Optional.of(planeta1));

        // Crear modelo nave
        ModeloNave modeloNave1 = new ModeloNave("modeloNave1", Double.valueOf(100), Double.valueOf(100),
                Double.valueOf(100));
        Mockito.when(modeloNaveRepository.findById(3l)).thenReturn(Optional.of(modeloNave1));

        // crear Nave
        Nave nave1 = new Nave("nave1", Double.valueOf(100), Double.valueOf(100), Double.valueOf(100), planeta1,
                modeloNave1);
        Mockito.when(naveRepository.findById(4l)).thenReturn(Optional.of(nave1));

        List<Tripulante> tripulantes = new ArrayList<Tripulante>();
        Tripulante tripulante1 = new Tripulante(5l, "Capitan", "1234", true, false, false, nave1);
        Tripulante tripulante2 = new Tripulante(6l, "Navegante", "1234", false, true, false, nave1);
        Tripulante tripulante3 = new Tripulante(7l, "Comerciante", "1234", false, false, true, nave1);
        Mockito.when(tripulanteRepository.findById(5l)).thenReturn(Optional.of(tripulante1));
        Mockito.when(tripulanteRepository.findById(6l)).thenReturn(Optional.of(tripulante2));
        Mockito.when(tripulanteRepository.findById(7l)).thenReturn(Optional.of(tripulante3));

        tripulantes.add(tripulante1);
        tripulantes.add(tripulante2);
        tripulantes.add(tripulante3);
        Mockito.when(tripulanteRepository.findAll()).thenReturn(tripulantes);

        Mockito.when(tripulanteRepository.findNaveByIdTripulante(5l)).thenReturn(nave1);
        Mockito.when(tripulanteRepository.findPlanetaByIdTripulante(5l)).thenReturn(planeta1);
        Mockito.when(tripulanteRepository.findEstrellaByIdTripulante(5l)).thenReturn(estrella1);
        Mockito.when(tripulanteRepository.findByUserAndPassword("Capitan", "1234")).thenReturn(tripulante1);
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerTripulante() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{id}", 5);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        String username = JsonPath.read(content, "$.username");

        assertEquals("Capitan", username);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerTripulantes() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante");
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        JSONArray object = new JSONArray(content);
        int size = object.length();

        assertEquals(3, size);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerNaveActual() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{id}/nave", 5);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        String nombreNave = JsonPath.read(content, "$.nombre");

        assertEquals("nave1", nombreNave);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerEstrellaActual() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{id}/estrella", 5);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        String nombreEstrella = JsonPath.read(content, "$.nombre");

        assertEquals("estrella1", nombreEstrella);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerPlanetaActual() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{id}/planeta", 5);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        String nombrePlaneta = JsonPath.read(content, "$.nombre");

        assertEquals("planeta1", nombrePlaneta);
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void getTripulanteLogin() throws Exception {
        String username = "Capitan";
        String password = "1234";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{usuario}/login/{password}",
                username, password);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        boolean booleanoCapitanTripulante = JsonPath.read(content, "$.capitan");
        String passwordTripulante = JsonPath.read(content, "$.password");

        assertEquals(true, booleanoCapitanTripulante);
        assertEquals(password, passwordTripulante);
        assertEquals(200, result.getResponse().getStatus());
    }

}
