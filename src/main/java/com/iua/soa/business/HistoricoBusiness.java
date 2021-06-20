package com.iua.soa.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.iua.soa.exceptions.BusinessException;
import com.iua.soa.exceptions.NotFoundException;
import com.iua.soa.model.Historico;
import com.iua.soa.repository.HistoricoRepository;

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
			final Gson gson = new Gson();
			Historico history = historico;
			String str=historico.toString();
			history.setRawData(str);
			
			return historicoDAO.save(history);
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

}
