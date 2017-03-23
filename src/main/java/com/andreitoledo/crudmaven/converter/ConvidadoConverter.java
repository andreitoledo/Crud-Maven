package com.andreitoledo.crudmaven.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.andreitoledo.crudmaven.dao.ConvidadoDAO;
import com.andreitoledo.crudmaven.model.Convidado;

@FacesConverter(forClass = Convidado.class)
public class ConvidadoConverter implements Converter {

	@Inject
	private ConvidadoDAO convidadoDAO;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		Convidado retorno = null;
		if (StringUtils.isNotEmpty(value)) {
			retorno = this.convidadoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {
			Long codigo = ((Convidado) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;

		}

		return "";
	}

}
