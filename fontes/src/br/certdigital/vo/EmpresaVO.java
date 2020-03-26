package br.certdigital.vo;

import java.sql.Date;
import java.util.List;

public class EmpresaVO {
	
	private List listaEmpresaVO;
	private List listaEstadoVO;
	private Long idEmpresa;
	private Long numCNPJ;
	private String numCNPJAsString;
	private String razaoSocial;
	private String nomeComercial;
	private Long idEntidade;
	private Long inscricaoEstadual;
	private String nomeContato;
	private String mail;
	private Long dddFone;
	private Long numFone;
	private Long dddFax;
	private Long numFax;
	private String enderecoComercial;
	private String bairroComercial;
	private String cidadeComercial;
	private String estadoComercial;
	private Long cepComercial;
	private String cepComercialAsString;
	private String enderecoCobranca;
	private String bairroCobranca;
	private String cidadeCobranca;
	private String estadoCobranca;
	private Long cepCobranca;
	private String cepCobrancaAsString;
	private Long operadorInc;
	private Date dataInc;
	private Long operadorAlt;
	private Date dataAlt;
	private Long dddCelular;
	private Long numCelular;
	
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
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getNumCNPJ() {
		return numCNPJ;
	}
	public void setNumCNPJ(Long numCNPJ) {
		this.numCNPJ = numCNPJ;
	}
	public String getNumCNPJAsString() {
		return numCNPJAsString;
	}
	public void setNumCNPJAsString(String numCNPJAsString) {
		this.numCNPJAsString = numCNPJAsString;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeComercial() {
		return nomeComercial;
	}
	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}
	public Long getIdEntidade() {
		return idEntidade;
	}
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}
	public Long getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(Long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getDddFone() {
		return dddFone;
	}
	public void setDddFone(Long dddFone) {
		this.dddFone = dddFone;
	}
	public Long getNumFone() {
		return numFone;
	}
	public void setNumFone(Long numFone) {
		this.numFone = numFone;
	}
	public Long getDddFax() {
		return dddFax;
	}
	public void setDddFax(Long dddFax) {
		this.dddFax = dddFax;
	}
	public Long getNumFax() {
		return numFax;
	}
	public void setNumFax(Long numFax) {
		this.numFax = numFax;
	}
	public String getEnderecoComercial() {
		return enderecoComercial;
	}
	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}
	public String getBairroComercial() {
		return bairroComercial;
	}
	public void setBairroComercial(String bairroComercial) {
		this.bairroComercial = bairroComercial;
	}
	public String getCidadeComercial() {
		return cidadeComercial;
	}
	public void setCidadeComercial(String cidadeComercial) {
		this.cidadeComercial = cidadeComercial;
	}
	public String getEstadoComercial() {
		return estadoComercial;
	}
	public void setEstadoComercial(String estadoComercial) {
		this.estadoComercial = estadoComercial;
	}
	public Long getCepComercial() {
		return cepComercial;
	}
	public void setCepComercial(Long cepComercial) {
		this.cepComercial = cepComercial;
	}
	public String getCepComercialAsString() {
		return cepComercialAsString;
	}
	public void setCepComercialAsString(String cepComercialAsString) {
		this.cepComercialAsString = cepComercialAsString;
	}
	public String getEnderecoCobranca() {
		return enderecoCobranca;
	}
	public void setEnderecoCobranca(String enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}
	public String getBairroCobranca() {
		return bairroCobranca;
	}
	public void setBairroCobranca(String bairroCobranca) {
		this.bairroCobranca = bairroCobranca;
	}
	public String getCidadeCobranca() {
		return cidadeCobranca;
	}
	public void setCidadeCobranca(String cidadeCobranca) {
		this.cidadeCobranca = cidadeCobranca;
	}
	public String getEstadoCobranca() {
		return estadoCobranca;
	}
	public void setEstadoCobranca(String estadoCobranca) {
		this.estadoCobranca = estadoCobranca;
	}
	public Long getCepCobranca() {
		return cepCobranca;
	}
	public void setCepCobranca(Long cepCobranca) {
		this.cepCobranca = cepCobranca;
	}
	public String getCepCobrancaAsString() {
		return cepCobrancaAsString;
	}
	public void setCepCobrancaAsString(String cepCobrancaAsString) {
		this.cepCobrancaAsString = cepCobrancaAsString;
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
	public Long getDddCelular() {
		return dddCelular;
	}
	public void setDddCelular(Long dddCelular) {
		this.dddCelular = dddCelular;
	}
	public Long getNumCelular() {
		return numCelular;
	}
	public void setNumCelular(Long numCelular) {
		this.numCelular = numCelular;
	}
		
}
