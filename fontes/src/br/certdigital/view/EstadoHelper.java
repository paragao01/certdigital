package br.certdigital.view;

import java.io.Serializable;
import java.util.List;

import br.certdigital.vo.EstadoVO;

public class EstadoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public EstadoVO estadoVO;
	public List listEstadoVO;
	
	/**
	 * @return Returns the estadoVO.
	 */
	public EstadoVO getEstadoVO() {
		return estadoVO;
	}
	/**
	 * @param estadoVO The estadoVO to set.
	 */
	public void setEstadoVO(EstadoVO estadoVO) {
		this.estadoVO = estadoVO;
	}
	/**
	 * @return Returns the listEstadoVO.
	 */
	public List getListEstadoVO() {
		return listEstadoVO;
	}
	/**
	 * @param listEstadoVO The listEstadoVO to set.
	 */
	public void setListEstadoVO(List listEstadoVO) {
		this.listEstadoVO = listEstadoVO;
	}
}
