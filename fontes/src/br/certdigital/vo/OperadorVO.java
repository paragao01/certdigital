package br.certdigital.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OperadorVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List listaAccess;
	private Long idOperador;
	private Long idTipoOperador;
	private Long idEntidade;
	private String nomeEntidade;		
	private Long idEmpresa;
	private String nomeEmpresa;	
	private String nomeTipoOperador;
	private String senha;
	private String nomeOperador;
	private String nomeMenu;
	private Date dataInc;
	private Long idOperadorInclusao;
	private String statusOperador;
	
	// Devido as novas funcionalidades da aplicacao (definicao de acesso)
	private MenuGerencialVO menuGerencialVO;
	private MenuOperacionalVO menuOperacionalVO;
	
	public OperadorVO() {
		if (menuGerencialVO == null) {
			menuGerencialVO = new MenuGerencialVO();
		}
		if (menuOperacionalVO == null) {
			menuOperacionalVO = new MenuOperacionalVO();
		}
	}

	public java.util.List getListaAccess() {
		return listaAccess;
	}

	public void setListaAccess(java.util.List listaAccess) {
		this.listaAccess = listaAccess;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

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

	public Long getIdTipoOperador() {
		return idTipoOperador;
	}

	public void setIdTipoOperador(Long idTipoOperador) {
		this.idTipoOperador = idTipoOperador;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeTipoOperador() {
		return nomeTipoOperador;
	}

	public void setNomeTipoOperador(String nomeTipoOperador) {
		this.nomeTipoOperador = nomeTipoOperador;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeOperador() {
		return nomeOperador;
	}

	public void setNomeOperador(String nomeOperador) {
		this.nomeOperador = nomeOperador;
	}

	public String getNomeMenu() {
		return nomeMenu;
	}

	public void setNomeMenu(String nomeMenu) {
		this.nomeMenu = nomeMenu;
	}

	public Date getDataInc() {
		return dataInc;
	}

	public void setDataInc(Date dataInc) {
		this.dataInc = dataInc;
	}

	public Long getIdOperadorInclusao() {
		return idOperadorInclusao;
	}

	public void setIdOperadorInclusao(Long idOperadorInclusao) {
		this.idOperadorInclusao = idOperadorInclusao;
	}

	public String getStatusOperador() {
		return statusOperador;
	}

	public void setStatusOperador(String statusOperador) {
		this.statusOperador = statusOperador;
	}

	public MenuGerencialVO getMenuGerencialVO() {
		return menuGerencialVO;
	}

	public void setMenuGerencialVO(MenuGerencialVO menuGerencialVO) {
		this.menuGerencialVO = menuGerencialVO;
	}

	public MenuOperacionalVO getMenuOperacionalVO() {
		return menuOperacionalVO;
	}

	public void setMenuOperacionalVO(MenuOperacionalVO menuOperacionalVO) {
		this.menuOperacionalVO = menuOperacionalVO;
	}
	
}