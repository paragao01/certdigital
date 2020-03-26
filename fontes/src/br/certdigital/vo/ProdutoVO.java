package br.certdigital.vo;

import java.io.Serializable;


public class ProdutoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idEntidade;
	private String nomeEntidade;		
	private Long idEmpresa;
	private String nomeEmpresa;	
	private Long idProduto;
	private String nomeProduto;
	private String pessoaProduto;	
	private String tipoProduto;
	private String validadeProduto;
	private Long idOperadorInclusao;

	public ProdutoVO() {}

	public Long getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	public String getNomeEntidade() {
		return nomeEntidade;
	}

	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getPessoaProduto() {
		return pessoaProduto;
	}

	public void setPessoaProduto(String pessoaProduto) {
		this.pessoaProduto = pessoaProduto;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getValidadeProduto() {
		return validadeProduto;
	}

	public void setValidadeProduto(String validadeProduto) {
		this.validadeProduto = validadeProduto;
	}

	public Long getIdOperadorInclusao() {
		return idOperadorInclusao;
	}

	public void setIdOperadorInclusao(Long idOperadorInclusao) {
		this.idOperadorInclusao = idOperadorInclusao;
	}
	
}