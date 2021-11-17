package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.service.ModeloNaveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * entidad ModeloNave
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/modelo-nave")
@CrossOrigin(origins = "http://localhost:4200")
public class ModeloNaveController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Inyección de dependencia del servicio de ModeloNave
     */
    @Autowired
    private ModeloNaveService modeloNaveService;

    // CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PostMapping("")
	@Operation(summary = "Crea un nuevo modeloNave")
	public ModeloNave crearModeloNave(@RequestBody ModeloNave modeloNaveNueva) {
		log.info("Creando ModeloNave");
		return modeloNaveService.crearModeloNave(modeloNaveNueva);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/{id}")
	@Operation(summary = "Obtiene un modeloNave por su id")
    public ModeloNave obtenerModeloNave(@PathVariable Long id) throws RecordNotFoundException {
        log.info("Obtener ModeloNave por ID");
        return modeloNaveService.obtenerModeloNave(id);
    }

	@GetMapping("")
	@Operation(summary = "Obtiene todos los modeloNaves")
	public List<ModeloNave> obtenerModeloNaves() {
		log.info("Obtener todos los ModeloNaves");
		return modeloNaveService.obtenerModeloNaves();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@PostMapping("/{id}")
	@Operation(summary = "Modifica un modeloNave")
	public ModeloNave modificarModeloNave(@RequestBody ModeloNave modeloNave, @PathVariable Long id) {
		log.info("modificar ModeloNave : " + modeloNave.getId());
		return modeloNaveService.modificarModeloNave(modeloNave, modeloNave.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina una modeloNave")
	public void eliminarModeloNaveById(@PathVariable Long id) {
		log.info("Eliminar ModeloNave por id" + id);
		modeloNaveService.eliminarModeloNave(id);
	}
}