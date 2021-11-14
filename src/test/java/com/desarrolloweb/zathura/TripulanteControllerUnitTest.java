package com.desarrolloweb.zathura;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @BeforeEach
    public void init() {
        // -------------------- Tripulantes --------------------
        List<Tripulante> tripulantes = new ArrayList<Tripulante>();
        Tripulante tripulante1 = new Tripulante(1l, "Capitan", "1234", true, false, false);
        Tripulante tripulante2 = new Tripulante(2l, "Navegante", "1234", false, true, false);
        Tripulante tripulante3 = new Tripulante(3l, "Comerciante", "1234", false, false, true);

        tripulantes.add(tripulante1);
        tripulantes.add(tripulante2);
        tripulantes.add(tripulante3);

        Mockito.when(tripulanteRepository.findById(1l)).thenReturn(Optional.of(tripulante1));
        Mockito.when(tripulanteRepository.findById(2l)).thenReturn(Optional.of(tripulante2));
        Mockito.when(tripulanteRepository.findById(3l)).thenReturn(Optional.of(tripulante3));
        Mockito.when(tripulanteRepository.findAll()).thenReturn(tripulantes);

    }

    @Test
    @WithMockUser(username = "Capitan", password = "1234")
    void obtenerTripulante() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/tripulante/{id}", 1);
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();

        String username = JsonPath.read(content, "$.username");

        assertEquals("Capitan",username);
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
    }

}
