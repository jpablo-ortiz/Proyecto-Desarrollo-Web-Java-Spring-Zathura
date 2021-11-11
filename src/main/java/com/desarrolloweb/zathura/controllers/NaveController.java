package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.NaveXProducto;
import com.desarrolloweb.zathura.models.POJOs.NavePojo;
import com.desarrolloweb.zathura.service.NaveService;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Esta clase contiene los métodos para el manejo de los servicios rest de la
 * entidad Nave
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/nave")
@CrossOrigin(origins = "http://localhost:4200")
public class NaveController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio de la entidad Nave
	 */
	@Autowired
	private NaveService naveService;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Crea una nueva nave")
	public Nave crearNave(@RequestBody NavePojo navePojo) throws RecordNotFoundException {
		log.info("Creando Nave");
		return naveService.crearNave(navePojo.navePojoToNave());
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene una nave por su id")
	public Nave obtenerNave(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Nave por ID");
		return naveService.obtenerNave(id);
	}

	@GetMapping("")
	@Operation(summary = "Obtiene todas las naves")
	public List<Nave> obtenerNaves() {
		log.info("Obtener todas las Naves");
		return naveService.obtenerNaves();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("/{id}")
	@Operation(summary = "Modifica una nave")
	public Nave modificarNave(@RequestBody Nave nave, @PathVariable Long id) {
		log.info("modificar Nave : " + nave.getId());
		return naveService.modificarNave(nave, nave.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina una nave")
	public void eliminarNaveById(@PathVariable Long id) {
		log.info("Eliminar Nave por id" + id);
		naveService.eliminarNave(id);
	}

	// ------------------------------------------------------------
	// -------------------------- OTHERS --------------------------
	// ------------------------------------------------------------

// PUEDE SER EL CAPITAN O EL NAVEGANTE 
//	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE')")
	// Cambiar planeta de una nave
	@GetMapping("/{idNave}/planeta/{idPlaneta}")
	@Operation(summary = "Cambia el planeta de una nave")
	public Nave cambiarPlaneta(@PathVariable Long idNave, @PathVariable Long idPlaneta) throws RecordNotFoundException {
		log.info("Cambiar planeta de una nave");
		return naveService.cambiarPlaneta(idNave, idPlaneta);
	}

	// Obtener NaveXProducto
	@GetMapping("/{naveId}/producto/{productoId}")
	@Operation(summary = "Obtener NaveXProducto")
	public NaveXProducto obtenerNaveXProducto(@PathVariable Long naveId, @PathVariable Long productoId)
			throws RecordNotFoundException {
		log.info("Cambiar planeta de una nave");
		return naveService.obtenerNaveXProducto(naveId, productoId);
	}

	// Realiza una compra de un producto en una nave
	//@PreAuthorize("hasRole('CAPITAN') or hasRole('COMERCIANTE')")
	@PostMapping(path = "/comprar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Realiza una compra de un producto")
	public String comprarProducto(@RequestBody String json) {
		log.info("Comprar producto");

		JSONObject mensaje = new JSONObject(json);
		Long idPlaneta = mensaje.getLong("idPlaneta");
		Long idProducto = mensaje.getLong("idProducto");
		Long idNave = mensaje.getLong("idNave");
		int cantidad = mensaje.getInt("cantidad");

		return naveService.comprarProducto(idPlaneta, idProducto, idNave, cantidad).toString();
	}

	// Realiza una venta de un producto en una nave
	//@PreAuthorize("hasRole('CAPITAN') or hasRole('COMERCIANTE')")
	@PostMapping(path = "/vender", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Realiza una venta de un producto")
	public String venderProducto(@RequestBody String json) {
		log.info("Vender producto");

		JSONObject mensaje = new JSONObject(json);
		Long idPlaneta = mensaje.getLong("idPlaneta");
		Long idProducto = mensaje.getLong("idProducto");
		Long idNave = mensaje.getLong("idNave");
		int cantidad = mensaje.getInt("cantidad");

		return naveService.venderProducto(idPlaneta, idProducto, idNave, cantidad).toString();
	}

	// Ingresar tripulante (id) a una nave (id)
	@GetMapping("/tripulante/{idTripulante}/nave/{idNave}")
	@Operation(summary = "Ingresar tripulante a una nave")
	public void ingresarTripulanteANave(@PathVariable Long idTripulante, @PathVariable Long idNave)
			throws RecordNotFoundException {
		log.info("Ingresar tripulante a una nave");
		naveService.ingresarTripulanteANave(idTripulante, idNave);
	}

}