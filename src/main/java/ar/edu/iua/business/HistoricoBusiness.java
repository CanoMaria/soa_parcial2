package ar.edu.iua.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Historico;
import ar.edu.iua.model.persistence.HistoricoRepository;
import springfox.documentation.spring.web.json.Json;


@Service
public class HistoricoBusiness implements IHistoricoBusiness{
	@Autowired
	private HistoricoRepository historicoDAO;
	
	@Override
	public Historico load(Long id) throws NotFoundException, BusinessException {
		Optional<Historico> op;
		try {
			op =  historicoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Orden con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Historico> list() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Historico add(Historico historico) throws BusinessException {
		try {
			return historicoDAO.save(historico);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Historico update(Historico historico) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Historico loadLast() throws NotFoundException, BusinessException {
		Optional<Historico> op;
		try {
			op =  historicoDAO.findLast();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	
		return op.get();
	}

}
