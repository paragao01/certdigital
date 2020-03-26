/*
 * Created on 25/05/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package br.certdigital.vo;

import java.io.Serializable;

public class MenuGerencialVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean alterarEntidade;
	private boolean consultarEntidade;
	
	private boolean alterarEmpresa;
	private boolean consultarEmpresa;
		
	private boolean alterarProduto;
	private boolean consultarProduto;
	
	private boolean alterarPreco;
	private boolean consultarPreco;
		
	private boolean consultarCertificado;
	private boolean consultarTabelaPreco;
	private boolean consultarFaturamento;
	
	public boolean isAlterarEntidade() {
		return alterarEntidade;
	}

	public void setAlterarEntidade(boolean alterarEntidade) {
		this.alterarEntidade = alterarEntidade;
	}

	public boolean isConsultarEntidade() {
		return consultarEntidade;
	}

	public void setConsultarEntidade(boolean consultarEntidade) {
		this.consultarEntidade = consultarEntidade;
	}

	public boolean isAlterarEmpresa() {
		return alterarEmpresa;
	}

	public void setAlterarEmpresa(boolean alterarEmpresa) {
		this.alterarEmpresa = alterarEmpresa;
	}

	public boolean isConsultarEmpresa() {
		return consultarEmpresa;
	}

	public void setConsultarEmpresa(boolean consultarEmpresa) {
		this.consultarEmpresa = consultarEmpresa;
	}

	public boolean isAlterarProduto() {
		return alterarProduto;
	}

	public void setAlterarProduto(boolean alterarProduto) {
		this.alterarProduto = alterarProduto;
	}

	public boolean isConsultarProduto() {
		return consultarProduto;
	}

	public void setConsultarProduto(boolean consultarProduto) {
		this.consultarProduto = consultarProduto;
	}

	public boolean isAlterarPreco() {
		return alterarPreco;
	}

	public void setAlterarPreco(boolean alterarPreco) {
		this.alterarPreco = alterarPreco;
	}

	public boolean isConsultarPreco() {
		return consultarPreco;
	}

	public void setConsultarPreco(boolean consultarPreco) {
		this.consultarPreco = consultarPreco;
	}

	public boolean isConsultarCertificado() {
		return consultarCertificado;
	}

	public void setConsultarCertificado(boolean consultarCertificado) {
		this.consultarCertificado = consultarCertificado;
	}

	public boolean isConsultarTabelaPreco() {
		return consultarTabelaPreco;
	}

	public void setConsultarTabelaPreco(boolean consultarTabelaPreco) {
		this.consultarTabelaPreco = consultarTabelaPreco;
	}

	public boolean isConsultarFaturamento() {
		return consultarFaturamento;
	}

	public void setConsultarFaturamento(boolean consultarFaturamento) {
		this.consultarFaturamento = consultarFaturamento;
	}

	public void setAllTrue() {
		
		alterarEntidade = true;
		consultarEntidade = true;
		alterarEmpresa = true;
		consultarEmpresa = true;
		alterarProduto = true;
		consultarProduto = true;
		alterarPreco = true;
		consultarPreco = true;
		consultarCertificado = true;
		consultarTabelaPreco = true;
		consultarFaturamento = true;
	}
	
	public void setAllFalse() {

		alterarEntidade = false;
		consultarEntidade = false;
		alterarEmpresa = false;
		consultarEmpresa = false;
		alterarProduto = false;
		consultarProduto = false;
		alterarPreco = false;
		consultarPreco = false;
		consultarCertificado = false;
		consultarTabelaPreco = false;
		consultarFaturamento = false;
	}
	
	/**
	 * OR com todos os atributos para verificar se existe pelo menos um que seja TRUE
	 *
	 * @return
	 */
	public boolean atLeastOneTrue() {
		
		return
		alterarEntidade ||
		consultarEntidade ||
		alterarEmpresa ||
		consultarEmpresa ||
		alterarProduto ||
		consultarProduto ||
		alterarPreco ||
		consultarPreco ||
		consultarCertificado ||
		consultarTabelaPreco ||
		consultarFaturamento;
		
	}
}
