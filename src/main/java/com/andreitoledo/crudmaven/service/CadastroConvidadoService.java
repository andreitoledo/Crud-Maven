package com.andreitoledo.crudmaven.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.andreitoledo.crudmaven.dao.ConvidadoDAO;
import com.andreitoledo.crudmaven.model.Convidado;
import com.andreitoledo.crudmaven.util.jpa.Transactional;

public class CadastroConvidadoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ConvidadoDAO convidadoDAO;	
	
	@Transactional
	public void salvar(Convidado convidado) throws NegocioException{
		if (convidado.getNome() == null || convidado.getNome().trim().equals("")) {			
			throw new NegocioException("O nome é obrigatório.");
			
		}
		
		this.convidadoDAO.salvar(convidado);		
		
	}

}
