package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.models.Nave;
import com.desarrolloweb.zathura.models.NaveXProducto;
import com.desarrolloweb.zathura.models.Planeta;
import com.desarrolloweb.zathura.models.PlanetaXProducto;
import com.desarrolloweb.zathura.models.Producto;
import com.desarrolloweb.zathura.repositories.NaveRepository;
import com.desarrolloweb.zathura.repositories.NaveXProductoRepository;
import com.desarrolloweb.zathura.repositories.PlanetaXProductoRepository;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para la entidad Nave
 *
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 */
@Service
public class NaveService {

	public static final double MAX_STOCK = 1000000;

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de la entidad Nave
	 */
	@Autowired
	private NaveRepository naveRepository;

	/**
	 * Inyección de dependencia del servicio de la entidad Planeta
	 */
	@Autowired
	private PlanetaService planetaService;

	/**
	 * Inyección de dependencia del servicio de la entidad ModeloNave
	 */
	@Autowired
	private ModeloNaveService modeloNaveService;

	/**
	 * Inyección de dependencia del servicio de la entidad Producto
	 */
	@Autowired
	private ProductoService productoService;

	/**
	 * Inyección de dependencia del repositorio de la entidad PlanetaXProducto
	 */
	@Autowired
	private PlanetaXProductoRepository planetaXProductoRepository;

		/**
	 * Inyección de dependencia del repositorio de la entidad NaveXProducto
	 */
	@Autowired
	private NaveXProductoRepository naveXProductoRepository;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear una entidad Nave
	 *
	 * @param nave Objeto de tipo Nave que contiene los datos de la nave a crear
	 * @return Objeto de tipo Nave que contiene los datos de la nave creada
	 */
	public Nave crearNave(Nave nave) {
		Planeta planetaPlantilla = nave.getPlanetaActual();
		if (planetaPlantilla != null) {
			try {
				planetaPlantilla = planetaService.obtenerPlaneta(planetaPlantilla.getId());
			} catch (RecordNotFoundException e) {
				planetaPlantilla = planetaService.crearPlaneta(planetaPlantilla);
			}
			nave.setPlanetaActual(planetaPlantilla);
		}

		ModeloNave modeloNavePlantilla = nave.getModeloNave();
		if (modeloNavePlantilla != null) {
			try {
				modeloNavePlantilla = modeloNaveService.obtenerModeloNave(modeloNavePlantilla.getId());
			} catch (RecordNotFoundException e) {
				modeloNavePlantilla = modeloNaveService.crearModeloNave(modeloNavePlantilla);
			}
			nave.setModeloNave(modeloNavePlantilla);
		}
		return naveRepository.save(nave);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener una entidad Nave
	 *
	 * @param id Identificador de la nave a obtener
	 * @return Objeto de tipo Nave que contiene los datos de la nave obtenida
	 * @throws RecordNotFoundException Excepción que permite notificar que no se
	 *                                 encontró el registro de la entidad Nave
	 */
	public Nave obtenerNave(Long id) throws RecordNotFoundException {
		Nave nave = naveRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro nave con id: " + id));
		return nave;
	}

	/**
	 * Método que permite obtener todas las entidades Nave
	 *
	 * @return Lista de objetos de tipo Nave que contiene los datos de todas las
	 *         naves obtenidas
	 */
	public List<Nave> obtenerNaves() {
		return (List<Nave>) naveRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite modificar una entidad Nave
	 *
	 * @param nave Objeto de tipo Nave que contiene los datos de la nave a modificar
	 * @return Objeto de tipo Nave que contiene los datos de la nave actualizada
	 */
	public Nave modificarNave(Nave plantilla, Long id) {
		return naveRepository.findById(id).map(nave -> {
			nave.setNombre(plantilla.getNombre());
			nave.setCargaActual(plantilla.getCargaActual());
			nave.setCantidadCredito(plantilla.getCantidadCredito());
			nave.setTotalTiempoViaje(plantilla.getTotalTiempoViaje());

			Planeta planetaPlantilla = plantilla.getPlanetaActual();
			if (planetaPlantilla != null) {
				try {
					planetaPlantilla = planetaService.obtenerPlaneta(planetaPlantilla.getId());
				} catch (RecordNotFoundException e) {
					planetaPlantilla = planetaService.crearPlaneta(planetaPlantilla);
				}
				nave.setPlanetaActual(planetaPlantilla);
			}

			ModeloNave modeloNavePlantilla = plantilla.getModeloNave();
			if (modeloNavePlantilla != null) {
				try {
					modeloNavePlantilla = modeloNaveService.obtenerModeloNave(modeloNavePlantilla.getId());
				} catch (RecordNotFoundException e) {
					modeloNavePlantilla = modeloNaveService.crearModeloNave(modeloNavePlantilla);
				}
				nave.setModeloNave(modeloNavePlantilla);
			}

			return naveRepository.save(nave);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return naveRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar una entidad Nave
	 *
	 * @param id Identificador de la nave a eliminar
	 */
	public void eliminarNave(Long id) {
		naveRepository.deleteById(id);
	}

	public Nave cambiarPlaneta(Long idNave, Long idPlaneta) throws RecordNotFoundException {
		Nave nave = obtenerNave(idNave);
		Planeta planeta = planetaService.obtenerPlaneta(idPlaneta);
		nave.setPlanetaActual(planeta);
		return naveRepository.save(nave);
	}

	public NaveXProducto obtenerNaveXProducto(Long naveId, Long productoId) {
		return naveRepository.findNaveXProducto(naveId, productoId);
	}



	public JSONObject comprarProducto(Long idPlaneta, Long idProducto, Long idNave, int cantidad) {
		JSONObject mensaje = new JSONObject();

		// SPRING
		// 1. Nave y producto
		// - crear una entidad NaveXProducto

		if (cantidad <= 0) {
			mensaje.put("mensaje", "La cantidad debe ser mayor a 0");
			mensaje.put("error", true);
			return mensaje;
		}

		Nave nave;
		Planeta planeta;
		Producto producto;

		try {
			nave = obtenerNave(idNave);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener la nave ingresada");
			mensaje.put("error", true);
			return mensaje;
		}

		try {
			planeta = planetaService.obtenerPlaneta(idPlaneta);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener el planeta ingresado");
			mensaje.put("error", true);
			return mensaje;
		}

		try {
			producto = productoService.obtenerProducto(idProducto);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener el producto ingresado");
			mensaje.put("error", true);
			return mensaje;
		}

		PlanetaXProducto planetaXProducto = planetaService.obtenerPlanetaXProducto(planeta.getId(), producto.getId());

		NaveXProducto naveXProducto = new NaveXProducto();

		// - Llenar los datos stock (cantidad de producto comprado), el totalCredito
		// (total a pagar) totalVolumen (total volumen del producto comprado), y nave y producto

		naveXProducto.setStock((double) cantidad);
		naveXProducto.setTotalCredito(cantidad * planetaXProducto.getPrecioCompra());
		naveXProducto.setTotalVolumen(cantidad * producto.getVolumen());
		naveXProducto.setNave(nave);
		naveXProducto.setProducto(producto);

		// - Dada la entidad anterior verificar:

		// - si el totalCredito es menor al credito disponible de la nave (continuar)
		// (Se hace el else para soltar solo el error en caso contrario)
		if (nave.getCantidadCredito() <= naveXProducto.getTotalCredito()) {
			mensaje.put("mensaje", "La nave no tiene suficiente credito para hacer la compra");
			mensaje.put("error", true);
			return mensaje;
		}

		// - si el totalVolumen es menor al volumen disponible de la nave (continuar)
		// (Se hace el else para soltar solo el error en caso contrario)
		if (nave.getCargaActual() <= naveXProducto.getTotalVolumen()) {
			mensaje.put("mensaje", "La nave no tiene suficiente volumen para hacer la compra");
			mensaje.put("error", true);
			return mensaje;
		}

		// - Si lo anterior es verdadero, entonces:

		// - restar los creditos de la nave de acuerto al total de compra
		nave.setCantidadCredito(nave.getCantidadCredito() - naveXProducto.getTotalCredito());

		// - restar el volumen-total de la nave
		nave.setCargaActual(nave.getCargaActual() - naveXProducto.getTotalVolumen());

		// - guardar la nave
		nave = naveRepository.save(nave);

		// Si ya existía una NaveXProducto con el mismo producto y nave, entonces:
		NaveXProducto naveXProductoExistente = null;
		try {
			naveXProductoExistente = obtenerNaveXProducto(nave.getId(), producto.getId());
		} catch (Exception e) {
			naveXProducto = naveXProductoRepository.save(naveXProducto);
		}

		if (naveXProductoExistente == null) {
			naveXProducto = naveXProductoRepository.save(naveXProducto);
		} else {
			naveXProductoExistente.setStock(naveXProductoExistente.getStock() + naveXProducto.getStock());
			naveXProductoExistente
					.setTotalCredito(naveXProductoExistente.getTotalCredito() + naveXProducto.getTotalCredito());
			naveXProductoExistente
					.setTotalVolumen(naveXProductoExistente.getTotalVolumen() + naveXProducto.getTotalVolumen());

			naveXProductoRepository.save(naveXProductoExistente);
		}

		// 2. Planeta y producto
		// - restar el producto en el stock del planeta
		planetaXProducto.setStock(planetaXProducto.getStock() - cantidad);

		// - Actualizar el PV (PV = FD/(1+S))
		planetaXProducto.setPrecioVenta(planetaXProducto.getFactorDemanda() / (1 + planetaXProducto.getStock()));

		// - Actualizar el PC (PC = FO/(1+S))
		planetaXProducto.setPrecioCompra(planetaXProducto.getFactorOferta() / (1 + planetaXProducto.getStock()));

		// - guardar el planetaXProducto
		planetaXProducto = planetaXProductoRepository.save(planetaXProducto);

		mensaje.put("mensaje", "Compra realizada con éxito");
		mensaje.put("error", false);
		return mensaje;
	}

	public JSONObject venderProducto(Long idPlaneta, Long idProducto, Long idNave, int cantidad) {
		JSONObject mensaje = new JSONObject();

		if (cantidad <= 0) {
			mensaje.put("mensaje", "La cantidad debe ser mayor a 0");
			mensaje.put("error", true);
			return mensaje;
		}

		// SPRING
		// 1. Nave y producto
		// - crear una entidad NaveXProducto
		Nave nave;
		Planeta planeta;
		Producto producto;
		
		try {
			nave = obtenerNave(idNave);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener la nave ingresada");
			mensaje.put("error", true);
			return mensaje;
		}

		try {
			planeta = planetaService.obtenerPlaneta(idPlaneta);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener el planeta ingresado");
			mensaje.put("error", true);
			return mensaje;
		}

		try {
			producto = productoService.obtenerProducto(idProducto);
		} catch (RecordNotFoundException e) {
			mensaje.put("mensaje", "Error al obtener el producto ingresado");
			mensaje.put("error", true);
			return mensaje;
		}

		PlanetaXProducto planetaXProducto = planetaService.obtenerPlanetaXProducto(planeta.getId(), producto.getId());

		// 1. El planeta necesita el producto x y yo tengo el producto x
		NaveXProducto naveXProductoActual;
		try {
			naveXProductoActual = obtenerNaveXProducto(nave.getId(), producto.getId());
		} catch (Exception e) {
			// 2. El planeta necesita el producto x y yo no lo tengo
			mensaje.put("mensaje", "La nave no tiene el producto ingresado");
			mensaje.put("error", true);
			return mensaje;
		}
		
		if (naveXProductoActual == null) {
			// 2. El planeta necesita el producto x y yo no lo tengo
			mensaje.put("mensaje", "La nave no tiene el producto ingresado");
			mensaje.put("error", true);
			return mensaje;
		}

		if (naveXProductoActual.getStock() < cantidad) {
			mensaje.put("mensaje", "La nave no tiene suficiente stock del producto");
			mensaje.put("error", true);
			return mensaje;
		}

		if (planetaXProducto.getStock() + cantidad > MAX_STOCK) {
			mensaje.put("mensaje", "El planeta no acepta la cantidad ingresada, ingrese una cantidad menor");
			mensaje.put("error", true);
			return mensaje;
		}

		// En el caso que el stock a vender sea menor al stock actual, entonces:
		naveXProductoActual.setStock(naveXProductoActual.getStock() - cantidad);
		naveXProductoActual.setTotalCredito(naveXProductoActual.getTotalCredito() + (cantidad * planetaXProducto.getPrecioVenta()));
		naveXProductoActual.setTotalVolumen(naveXProductoActual.getTotalVolumen() + (cantidad * producto.getVolumen()));

		if (naveXProductoActual.getStock() == 0) {
			naveXProductoRepository.delete(naveXProductoActual);
		} else {
			naveXProductoRepository.save(naveXProductoActual);
		}

		// 2. Planeta y producto
		// - restar el producto en el stock del planeta
		planetaXProducto.setStock(planetaXProducto.getStock() + cantidad);

		// - Actualizar el PV (PV = FD/(1+S))
		planetaXProducto.setPrecioVenta(planetaXProducto.getFactorDemanda() / (1 + planetaXProducto.getStock()));

		// - Actualizar el PC (PC = FO/(1+S))
		planetaXProducto.setPrecioCompra(planetaXProducto.getFactorOferta() / (1 + planetaXProducto.getStock()));
	
		// - guardar el planetaXProducto
		planetaXProducto = planetaXProductoRepository.save(planetaXProducto);

		mensaje.put("mensaje", "Venta realizada con éxito");
		mensaje.put("error", false);
		return mensaje;
	}

}
