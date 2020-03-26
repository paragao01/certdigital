package br.certdigital.vo;

import java.io.Serializable;
import java.util.Date;

public class PrecoVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idEntidade;
	private String nomeEntidade;		
	private Long idEmpresa;
	private String nomeEmpresa;	
	private Long idProduto;
	private String nomeProduto;
	private Long idPreco;
	private Date dataReferencia;
	private Double precoSugerido;
	private Double precoEntidade;		
	private String dataReferenciaAsString;	
	private String precoEntidadeAsString;
	private String precoSugeridoAsString;	
	private Long idOperadorInclusao;

	public PrecoVO() {}

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

	public Long getIdPreco() {
		return idPreco;
	}

	public void setIdPreco(Long idPreco) {
		this.idPreco = idPreco;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public Double getPrecoSugerido() {
		return precoSugerido;
	}

	public void setPrecoSugerido(Double precoSugerido) {
		this.precoSugerido = precoSugerido;
	}

	public Double getPrecoEntidade() {
		return precoEntidade;
	}

	public void setPrecoEntidade(Double precoEntidade) {
		this.precoEntidade = precoEntidade;
	}

	public String getDataReferenciaAsString() {
		return dataReferenciaAsString;
	}

	public void setDataReferenciaAsString(String dataReferenciaAsString) {
		this.dataReferenciaAsString = dataReferenciaAsString;
	}

	public String getPrecoEntidadeAsString() {
		return precoEntidadeAsString;
	}

	public void setPrecoEntidadeAsString(String precoEntidadeAsString) {
		this.precoEntidadeAsString = precoEntidadeAsString;
	}

	public String getPrecoSugeridoAsString() {
		return precoSugeridoAsString;
	}

	public void setPrecoSugeridoAsString(String precoSugeridoAsString) {
		this.precoSugeridoAsString = precoSugeridoAsString;
	}

	public Long getIdOperadorInclusao() {
		return idOperadorInclusao;
	}

	public void setIdOperadorInclusao(Long idOperadorInclusao) {
		this.idOperadorInclusao = idOperadorInclusao;
	}
	
}
