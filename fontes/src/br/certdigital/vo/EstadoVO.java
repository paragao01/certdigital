package br.certdigital.vo;

public class EstadoVO {
	private String sigla;
	private String descricao;
	
	/**
	 * @return Returns the descricao.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao The descricao to set.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return Returns the sigla.
	 */
	public String getSigla() {
		return sigla;
	}
	/**
	 * @param sigla The sigla to set.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
