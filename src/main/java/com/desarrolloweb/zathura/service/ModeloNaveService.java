package com.desarrolloweb.zathura.service;

import java.util.List;

import com.desarrolloweb.zathura.exceptions.RecordNotFoundException;
import com.desarrolloweb.zathura.models.ModeloNave;
import com.desarrolloweb.zathura.repositories.ModeloNaveRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Esta clase contiene los servicios para los modelos de las naves
 * 
 * @author Kenneth Leonel Triana
 * @author Juan Pablo Ortiz Rubio
 * @version 2.0.0
 *
 */
@Service
public class ModeloNaveService {

	/**
	 * Objeto que permite el registro de trazas de la ejecución de las operaciones
	 * de la clase
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Inyección de dependencia del repositorio de los modelos de las naves
	 */
    @Autowired
    private ModeloNaveRepository modeloNaveRepository;

	// CRUD - CREATE - READ - UPDATE - DELETE

	// ------------------------------------------------------------
	// -------------------------- CREATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite crear un modelo de nave
	 * 
	 * @param modeloNave
	 *            Objeto de tipo ModeloNave que contiene la información de
	 * 		      la entidad modelo de nave
	 * @return Modelo de nave creado
	 */
	public ModeloNave create(ModeloNave modeloNave) {
		return modeloNaveRepository.save(modeloNave);
	}

	// ------------------------------------------------------------
	// --------------------------- READ ---------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite obtener un modelo de nave
	 * 
	 * @param id
	 *            Identificador del modelo de nave
	 * @return Modelo de nave obtenido
	 * @throws RecordNotFoundException
	 *             Excepción que se arroja cuando no se encuentra el modelo de
	 *             nave
	 */
	public ModeloNave obtenerModeloNave(Long id) throws RecordNotFoundException {
		ModeloNave modeloNave = modeloNaveRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("No se encontro modelo nave con id: " + id));
		return modeloNave;
	}

	/**
	 * Método que permite obtener todos los modelos de nave
	 * 
	 * @return Lista de modelos de nave obtenidos
	 */
	public List<ModeloNave> obtenerModeloNave() {
		return (List<ModeloNave>) modeloNaveRepository.findAll();
	}

	// ------------------------------------------------------------
	// -------------------------- UPDATE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite actualizar un modelo de nave
	 * 
	 * @param modeloNave
	 *            Objeto de tipo ModeloNave que contiene la información de
	 * 		      la entidad modelo de nave
	 * @return Modelo de nave actualizado
	 */
	public ModeloNave actualizarModeloNave(ModeloNave plantilla, Long id) {
		return modeloNaveRepository.findById(id).map(modeloNave -> {
			modeloNave.setNombreModelo(plantilla.getNombreModelo());
			modeloNave.setCargaMax(plantilla.getCargaMax());
			modeloNave.velocidadMax(plantilla.getVelocidadMax());

			return modeloNaveRepository.save(modeloNave);
		}).orElseGet(() -> {
			plantilla.setId(id);
			return modeloNaveRepository.save(plantilla);
		});
	}

	// ------------------------------------------------------------
	// -------------------------- DELETE --------------------------
	// ------------------------------------------------------------

	/**
	 * Método que permite eliminar un modelo de nave
	 * 
	 * @param id
	 *            Identificador del modelo de nave
	 */
	public void eliminarModeloNave(Long id) {
		modeloNaveRepository.deleteById(id);
	}

}
