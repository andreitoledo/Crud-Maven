package com.andreitoledo.crudmaven.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andreitoledo.crudmaven.dao.ConvidadoDAO;
import com.andreitoledo.crudmaven.model.Convidado;
import com.andreitoledo.crudmaven.service.NegocioException;
import com.andreitoledo.crudmaven.util.jsf.FacesMessages;

@Named
@ViewScoped
public class PesquisaConvidadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ConvidadoDAO convidadoDAO;

	private List<Convidado> convidados = new ArrayList<>();

	private Convidado convidadoSelecionado;

	@Inject
	private FacesMessages facesMessages;

	// get da lista convidados
	public List<Convidado> getConvidados() {
		return convidados;
	}

	// carrego a lista no carregamento da tela
	@PostConstruct
	public void inicializar() {
		convidados = convidadoDAO.buscarTodos();
	}

	public void excluir() {
		try {
			convidadoDAO.excluir(convidadoSelecionado);
			this.convidados.remove(convidadoSelecionado);
			facesMessages.info("Convidado " + convidadoSelecionado.getNome()
					+ " excluído com sucesso.");

		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());

		}
	}

	public Convidado getConvidadoSelecionado() {
		return convidadoSelecionado;
	}

	public void setConvidadoSelecionado(Convidado convidadoSelecionado) {
		this.convidadoSelecionado = convidadoSelecionado;
	}

}
