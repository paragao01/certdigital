package br.certdigital.shared.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BaseForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String metodo;
	private String operation;

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Reset all properties to their default values.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param request
	 *            The HTTP Request we are processing.
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}

}
