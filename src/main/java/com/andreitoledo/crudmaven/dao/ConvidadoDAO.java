package com.andreitoledo.crudmaven.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.andreitoledo.crudmaven.model.Convidado;
import com.andreitoledo.crudmaven.service.NegocioException;
import com.andreitoledo.crudmaven.util.jpa.Transactional;

public class ConvidadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Convidado buscarPeloCodigo(Long codigo) {
		return manager.find(Convidado.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Convidado> buscarTodos() {
		return manager.createQuery("select c from Convidado c").getResultList();
	}

	public void salvar(Convidado convidado) {
		manager.merge(convidado);
	}

	@Transactional
	public void excluir(Convidado convidado) throws NegocioException {

		convidado = buscarPeloCodigo(convidado.getCodigo());
		
		try {
			manager.remove(convidado);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este Convidado não pode ser exluído.");
		}

	}

}
