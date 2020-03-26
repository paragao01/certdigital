package br.certdigital.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * VO filtro para consultas
 */
public class FiltroConsultaVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tipo_pessoa;
	private Long documento;
	private String documentoAsString;
	private Long idEmpresa;
	private String nomeEmpresa;
	private Long idOperador;
	private String nomeOperador;
	private String dataInicialAsString;
	private Date dataInicial;
	private String dataFinalAsString;
	private Date dataFinal;
	private Long idMensagem;
	private int mes;
	private int ano;	
		
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
	public Long getIdOperador() {
		return idOperador;
	}
	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}
	public String getNomeOperador() {
		return nomeOperador;
	}
	public void setNomeOperador(String nomeOperador) {
		this.nomeOperador = nomeOperador;
	}
	public String getDataInicialAsString() {
		return dataInicialAsString;
	}
	public void setDataInicialAsString(String dataInicialAsString) {
		this.dataInicialAsString = dataInicialAsString;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinalAsString() {
		return dataFinalAsString;
	}
	public void setDataFinalAsString(String dataFinalAsString) {
		this.dataFinalAsString = dataFinalAsString;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Long getIdMensagem() {
		return idMensagem;
	}
	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}	
}
