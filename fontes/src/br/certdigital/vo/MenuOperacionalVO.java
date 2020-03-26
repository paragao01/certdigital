/*
 * Created on 25/05/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.certdigital.vo;

import java.io.Serializable;

public class MenuOperacionalVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean alterarOperador;
	private boolean consultarOperador;
	
	private boolean alterarCertificado;
	private boolean consultarCertificado;

	public boolean isAlterarOperador() {
		return alterarOperador;
	}

	public void setAlterarOperador(boolean alterarOperador) {
		this.alterarOperador = alterarOperador;
	}

	public boolean isConsultarOperador() {
		return consultarOperador;
	}

	public void setConsultarOperador(boolean consultarOperador) {
		this.consultarOperador = consultarOperador;
	}

	public boolean isAlterarCertificado() {
		return alterarCertificado;
	}

	public void setAlterarCertificado(boolean alterarCertificado) {
		this.alterarCertificado = alterarCertificado;
	}

	public boolean isConsultarCertificado() {
		return consultarCertificado;
	}

	public void setConsultarCertificado(boolean consultarCertificado) {
		this.consultarCertificado = consultarCertificado;
	}

	public void setAllTrue() {
		
		alterarOperador = true;
		consultarOperador = true;
		
		alterarCertificado = true;
		consultarCertificado = true;
				
	}
	
	public void setAllFalse() {
		
		alterarOperador = false;
		consultarOperador = false;
		
		alterarCertificado = false;
		consultarCertificado = false;
				
	}
	
	/**
	 * OR com todos os atributos para verificar se existe pelo menos um que seja TRUE
	 *
	 * @return
	 */
	public boolean atLeastOneTrue() {
		
		return
		alterarOperador ||
		consultarOperador ||
		alterarCertificado ||
		consultarCertificado;

	}
}
