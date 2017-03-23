package com.andreitoledo.crudmaven.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andreitoledo.crudmaven.dao.ConvidadoDAO;
import com.andreitoledo.crudmaven.model.Convidado;
import com.andreitoledo.crudmaven.service.CadastroConvidadoService;
import com.andreitoledo.crudmaven.service.NegocioException;
import com.andreitoledo.crudmaven.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CadastroConvidadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroConvidadoService cadastroConvidadoService;

	private Convidado convidado;

	private List<Convidado> convidados;

	@Inject
	private ConvidadoDAO convidadoDAO;

	@Inject
	private FacesMessages facesMessages;

	// instancia Convidado para não dar nulo.
	public void inicializar() {
		if (convidado == null) {
			limpar();

		}

		// carrega a lista de convidados
		this.convidados = this.convidadoDAO.buscarTodos();
	}

	private void limpar() {
		this.convidado = new Convidado();
	}

	public void salvar() {
		try {
			this.cadastroConvidadoService.salvar(convidado);
			facesMessages.info("Convidado salvo com sucesso.");

			limpar();

		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());

		}
	}

	public Convidado getConvidado() {
		return convidado;
	}

	public void setConvidado(Convidado convidado) {
		this.convidado = convidado;
	}

	public List<Convidado> getConvidados() {
		return convidados;
	}

	public boolean isEditando() {
		return this.convidado.getCodigo() != null;
	}

}
