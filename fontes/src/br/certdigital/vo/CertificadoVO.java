package br.certdigital.vo;

import java.util.Date;
import java.util.List;

public class CertificadoVO {
	
	private List listaEmpresaVO;
	private List listaEstadoVO;
	private List listaprodutoVO;
	private Long idEntidade;	
	private Long idEmpresa;
	private Long idProduto;
	private Long idCertificado;
	private String tipo_pessoa;
	private Long documento;
	private String documentoAsString;
	private String razaoRequerente;
	private String fantasiaRequerente;
	private Long inssRequerente;
	private String cidadeRequerente;
	private String estadoRequerente;
	private Long dddRequerente;
	private Long telRequerente;
	private String nomeResponsavel;
	private Long cpfResponsavel;
	private String cpfResponsavelAsString;
	private Date dataNascResponsavel;
	private String dataNascResponsavelAsString;	
	private String emailResponsavel;
	private String logradouroResponsavel;
	private String numeroResponsavel;
	private String complementoResponsavel;
	private String bairroResponsavel;
	private String cidadeResponsavell;
	private String estadoResponsavel;
	private Long cepResponsavel;
	private String cepResponsavelAsString;
	private Long dddResponsavel;
	private Long telResponsavel;
	private Long operadorInc;
	private Date dataInc;
	private Long operadorAlt;
	private Date dataAlt;
	private Date dataEmissao;
	private String dataEmissaoAsString;
	private String associado;
	private Long protocolo;
	
	
	public CertificadoVO() {}

	public List getListaEmpresaVO() {
		return listaEmpresaVO;
	}

	public void setListaEmpresaVO(List listaEmpresaVO) {
		this.listaEmpresaVO = listaEmpresaVO;
	}

	public List getListaEstadoVO() {
		return listaEstadoVO;
	}

	public void setListaEstadoVO(List listaEstadoVO) {
		this.listaEstadoVO = listaEstadoVO;
	}

	public List getListaprodutoVO() {
		return listaprodutoVO;
	}

	public void setListaprodutoVO(List listaprodutoVO) {
		this.listaprodutoVO = listaprodutoVO;
	}

	public Long getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdCertificado() {
		return idCertificado;
	}

	public void setIdCertificado(Long idCertificado) {
		this.idCertificado = idCertificado;
	}

	public Long getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(Long cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getCpfResponsavelAsString() {
		return cpfResponsavelAsString;
	}

	public void setCpfResponsavelAsString(String cpfResponsavelAsString) {
		this.cpfResponsavelAsString = cpfResponsavelAsString;
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

	public String getFantasiaRequerente() {
		return fantasiaRequerente;
	}

	public void setFantasiaRequerente(String fantasiaRequerente) {
		this.fantasiaRequerente = fantasiaRequerente;
	}

	public Long getInssRequerente() {
		return inssRequerente;
	}

	public void setInssRequerente(Long inssRequerente) {
		this.inssRequerente = inssRequerente;
	}

	public String getCidadeRequerente() {
		return cidadeRequerente;
	}

	public void setCidadeRequerente(String cidadeRequerente) {
		this.cidadeRequerente = cidadeRequerente;
	}

	public String getEstadoRequerente() {
		return estadoRequerente;
	}

	public void setEstadoRequerente(String estadoRequerente) {
		this.estadoRequerente = estadoRequerente;
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

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Date getDataNascResponsavel() {
		return dataNascResponsavel;
	}

	public void setDataNascResponsavel(Date dataNascResponsavel) {
		this.dataNascResponsavel = dataNascResponsavel;
	}

	public String getDataNascResponsavelAsString() {
		return dataNascResponsavelAsString;
	}

	public void setDataNascResponsavelAsString(String dataNascResponsavelAsString) {
		this.dataNascResponsavelAsString = dataNascResponsavelAsString;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public String getLogradouroResponsavel() {
		return logradouroResponsavel;
	}

	public void setLogradouroResponsavel(String logradouroResponsavel) {
		this.logradouroResponsavel = logradouroResponsavel;
	}

	public String getNumeroResponsavel() {
		return numeroResponsavel;
	}

	public void setNumeroResponsavel(String numeroResponsavel) {
		this.numeroResponsavel = numeroResponsavel;
	}

	public String getComplementoResponsavel() {
		return complementoResponsavel;
	}

	public void setComplementoResponsavel(String complementoResponsavel) {
		this.complementoResponsavel = complementoResponsavel;
	}

	public String getBairroResponsavel() {
		return bairroResponsavel;
	}

	public void setBairroResponsavel(String bairroResponsavel) {
		this.bairroResponsavel = bairroResponsavel;
	}

	public String getCidadeResponsavell() {
		return cidadeResponsavell;
	}

	public void setCidadeResponsavell(String cidadeResponsavell) {
		this.cidadeResponsavell = cidadeResponsavell;
	}

	public String getEstadoResponsavel() {
		return estadoResponsavel;
	}

	public void setEstadoResponsavel(String estadoResponsavel) {
		this.estadoResponsavel = estadoResponsavel;
	}

	public Long getCepResponsavel() {
		return cepResponsavel;
	}

	public void setCepResponsavel(Long cepResponsavel) {
		this.cepResponsavel = cepResponsavel;
	}

	public String getCepResponsavelAsString() {
		return cepResponsavelAsString;
	}

	public void setCepResponsavelAsString(String cepResponsavelAsString) {
		this.cepResponsavelAsString = cepResponsavelAsString;
	}

	public Long getDddResponsavel() {
		return dddResponsavel;
	}

	public void setDddResponsavel(Long dddResponsavel) {
		this.dddResponsavel = dddResponsavel;
	}

	public Long getTelResponsavel() {
		return telResponsavel;
	}

	public void setTelResponsavel(Long telResponsavel) {
		this.telResponsavel = telResponsavel;
	}

	public Long getOperadorInc() {
		return operadorInc;
	}

	public void setOperadorInc(Long operadorInc) {
		this.operadorInc = operadorInc;
	}

	public Date getDataInc() {
		return dataInc;
	}

	public void setDataInc(Date dataInc) {
		this.dataInc = dataInc;
	}

	public Long getOperadorAlt() {
		return operadorAlt;
	}

	public void setOperadorAlt(Long operadorAlt) {
		this.operadorAlt = operadorAlt;
	}

	public Date getDataAlt() {
		return dataAlt;
	}

	public void setDataAlt(Date dataAlt) {
		this.dataAlt = dataAlt;
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

	public Long getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}
}
