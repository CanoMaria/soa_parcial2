package ar.edu.iua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.business.IHistoricoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Historico;
import springfox.documentation.spring.web.json.Json;




@RestController
@RequestMapping(value = Constantes.URL_HISTORICO)
public class HistoricoRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IHistoricoBusiness historicoBusiness;
	
	//---------Listar por ID------------------
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Historico> load(@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<Historico>(historicoBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Historico>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Historico>(HttpStatus.NOT_FOUND);
		}
	}
	//---------Guardar Historico en BD------------------
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Historico historico) {
		try {
			
			historicoBusiness.add(historico);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_HISTORICO + '/' + "1" );//historico.getId_historico()
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//---------Ultimo valor ------------------
		@GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Historico> loadLasted() {

			try {
				return new ResponseEntity<Historico>(historicoBusiness.loadLast(), HttpStatus.OK);
			} catch (BusinessException e) {
				return new ResponseEntity<Historico>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<Historico>(HttpStatus.NOT_FOUND);
			}
		}

}
