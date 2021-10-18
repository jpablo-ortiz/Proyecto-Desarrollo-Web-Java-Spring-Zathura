package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.repositories.ProductoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Producto
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class ProductoService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Producto
	 */
    @Autowired
    private ProductoRepository productoRepository;

    // CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear un registro de la entidad Producto
	 *
	 * @param producto Objeto de la entidad Producto que contiene la información del producto a crear
	 * @return Objeto de la entidad Producto con la información del producto creado
	 */
	public Producto crearPlaneta(Producto producto) {
		return productoRepository.save(producto);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener un registro de la entidad Producto
	 *
	 * @param id Identificador del producto a obtener
	 * @return Objeto de la entidad Producto con la información del producto obtenido
	 * @throws RecordNotFoundException Si no existe el producto con el identificador dado
	 */
	public Producto obtenerProducto(Long id) throws RecordNotFoundException {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro producto con id: " + id));
		return producto;
	}

	/**
	 * Método que permite obtener todos los registros de la entidad Producto
	 *
	 * @return Lista de objetos de la entidad Producto con la información de todos los productos obtenidos
	 */
	public List<Producto> obtenerProductos() {
		return (List<Producto>) productoRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar un registro de la entidad Producto
	 *
	 * @param id Identificador del producto a modificar
	 * @param producto Objeto de la entidad Producto con la información del producto a modificar
	 * @return Objeto de la entidad Producto con la información del producto actualizado
	 */
	public Producto modificarNave(Producto plantilla, Long id) {
		return productoRepository.findById(id).map(producto -> {
			producto.setNombre(plantilla.getNombre());
			producto.setCostoCredito(plantilla.getCostoCredito());
			producto.setVolumen(plantilla.getVolumen());
			producto.setPeso(plantilla.getPeso());
			
			return productoRepository.save(producto);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return productoRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar un registro de la entidad Producto
	 *
	 * @param id Identificador del producto a eliminar
	 */
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
	}

}
