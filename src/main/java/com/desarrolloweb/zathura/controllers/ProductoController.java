package com.desarrolloweb.zathura.controllers;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.service.ProductoService;

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
 * entidad producto
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del servicio del producto
	 */
    @Autowired
    private ProductoService productoService;

    // CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@PostMapping("")
	@Operation(summary = "Crea un nuevo producto")
	public Producto crearProducto(@RequestBody Producto productoNueva) {
		log.info("Creando Producto");
		return productoService.crearProducto(productoNueva);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("/{id}")
	@Operation(summary = "Obtiene un producto por su id")
	public Producto obtenerProducto(@PathVariable Long id) throws RecordNotFoundException {
		log.info("Obtener Producto por ID");
		return productoService.obtenerProducto(id);
	}

	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@GetMapping("")
	@Operation(summary = "Obtiene todos los productos")
	public List<Producto> obtenerProductos() {
		log.info("Obtener todos los Productos");
		return productoService.obtenerProductos();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	@PreAuthorize("hasRole('CAPITAN') or hasRole('COMERCIANTE')")
	@PostMapping("/{id}")
	@Operation(summary = "Modifica un producto")
	public Producto modificarProducto(@RequestBody Producto producto, @PathVariable Long id) {
		log.info("modificar Producto : " + producto.getId());
		return productoService.modificarProducto(producto, producto.getId());
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------
	@PreAuthorize("hasRole('CAPITAN') or hasRole('NAVEGANTE') or hasRole('COMERCIANTE')")
	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un producto")
	public void eliminarProductoById(@PathVariable Long id) {
		log.info("Eliminar Producto por id" + id);
		productoService.eliminarProducto(id);
	}

}