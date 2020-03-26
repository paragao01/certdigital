package br.certdigital.vo;

import java.io.Serializable;
import java.util.Date;

public class ItemConsultaVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nomeProduto;
	private Double custoEntidade;
	private String custoEntidadeAsString;	
	private Double custoSugerido;
	private String custoSugeridoAsString;	
	private String tipo_pessoa;
	private Long documento;
	private String documentoAsString;
	private String razaoRequerente;
	private String tipoProduto;
	private String validadeProduto;
	private String nomeResponsavel;
	private String emailResponsavel;
	private Long dddRequerente;
	private Long telRequerente;
	private Date dataEmissao;
	private String dataEmissaoAsString;
	private String associado;
	private Long percentual;
	
	public ItemConsultaVO() {}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Double getCustoEntidade() {
		return custoEntidade;
	}
	public void setCustoEntidade(Double custoEntidade) {
		this.custoEntidade = custoEntidade;
	}
	public String getCustoEntidadeAsString() {
		return custoEntidadeAsString;
	}

	public void setCustoEntidadeAsString(String custoEntidadeAsString) {
		this.custoEntidadeAsString = custoEntidadeAsString;
	}

	public Double getCustoSugerido() {
		return custoSugerido;
	}
	public void setCustoSugerido(Double custoSugerido) {
		this.custoSugerido = custoSugerido;
	}
	public String getCustoSugeridoAsString() {
		return custoSugeridoAsString;
	}

	public void setCustoSugeridoAsString(String custoSugeridoAsString) {
		this.custoSugeridoAsString = custoSugeridoAsString;
	}

	public String getTipo_pessoa() {
		return tipo_pessoa;
	}

	public void setTipo_pessoa(String tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getDocumentoAsString() {
		return documentoAsString;
	}

	public void setDocumentoAsString(String documentoAsString) {
		this.documentoAsString = documentoAsString;
	}

	public String getRazaoRequerente() {
		return razaoRequerente;
	}

	public void setRazaoRequerente(String razaoRequerente) {
		this.razaoRequerente = razaoRequerente;
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

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public Long getDddRequerente() {
		return dddRequerente;
	}

	public void setDddRequerente(Long dddRequerente) {
		this.dddRequerente = dddRequerente;
	}

	public Long getTelRequerente() {
		return telRequerente;
	}

	public void setTelRequerente(Long telRequerente) {
		this.telRequerente = telRequerente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getDataEmissaoAsString() {
		return dataEmissaoAsString;
	}

	public void setDataEmissaoAsString(String dataEmissaoAsString) {
		this.dataEmissaoAsString = dataEmissaoAsString;
	}

	public String getAssociado() {
		return associado;
	}

	public void setAssociado(String associado) {
		this.associado = associado;
	}

	public Long getPercentual() {
		return percentual;
	}

	public void setPercentual(Long percentual) {
		this.percentual = percentual;
	}

}
