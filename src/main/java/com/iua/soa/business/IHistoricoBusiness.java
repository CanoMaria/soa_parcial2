package com.iua.soa.business;

import java.util.List;

import com.iua.soa.exceptions.BusinessException;
import com.iua.soa.exceptions.NotFoundException;
import com.iua.soa.model.Historico;

import net.minidev.json.JSONObject;



public interface IHistoricoBusiness {
	
	public Historico load(Long id) throws NotFoundException, BusinessException;

	public List<Historico> list() throws BusinessException;

	public Historico add(Historico historico) throws BusinessException;

	public Historico update(Historico historico) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;
}
