package ar.edu.iua.business;

import java.util.List;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Historico;





public interface IHistoricoBusiness {
	
	public Historico load(Long id) throws NotFoundException, BusinessException;

	public List<Historico> list() throws BusinessException;

	public Historico add(Historico historico) throws BusinessException;

	public Historico update(Historico historico) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;

	public Historico loadLast()throws NotFoundException, BusinessException;
}
