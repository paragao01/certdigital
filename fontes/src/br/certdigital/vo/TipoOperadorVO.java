package br.certdigital.vo;

import java.io.Serializable;

public class TipoOperadorVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long codigoTipoOperador;
	private String descricaoTipoOperador;
	
	/**
	 * @return Returns the codigoTipoOperador.
	 */
	public Long getCodigoTipoOperador() {
		return codigoTipoOperador;
	}
	/**
	 * @param codigoTipoOperador The codigoTipoOperador to set.
	 */
	public void setCodigoTipoOperador(Long codigoTipoOperador) {
		this.codigoTipoOperador = codigoTipoOperador;
	}
	/**
	 * @return Returns the descricaoTipoOperador.
	 */
	public String getDescricaoTipoOperador() {
		return descricaoTipoOperador;
	}
	/**
	 * @param descricaoTipoOperador The descricaoTipoOperador to set.
	 */
	public void setDescricaoTipoOperador(String descricaoTipoOperador) {
		this.descricaoTipoOperador = descricaoTipoOperador;
	}
}
