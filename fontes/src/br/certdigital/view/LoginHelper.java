package br.certdigital.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.certdigital.vo.OperadorVO;

public class LoginHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OperadorVO operadorVO;
	
	private List listaOperadorVO;
	
	private List listaTipoOperadorVO;
	
	public LoginHelper(){		
		operadorVO = new OperadorVO();
		listaOperadorVO = Collections.EMPTY_LIST;
		listaTipoOperadorVO = Collections.EMPTY_LIST;
	}
	
	/**
	 * @return Returns the operadorVo.
	 */
	public OperadorVO getOperadorVO() {
		return operadorVO;
	}
	/**
	 * @param operadorVo The operadorVo to set.
	 */
	public void setOperadorVO(OperadorVO operadorVo) {
		this.operadorVO = operadorVo;
	}
	/**
	 * @return Returns the listaOperadorVO.
	 */
	public List getListaOperadorVO() {
		return listaOperadorVO;
	}
	/**
	 * @param listaOperadorVO The listaOperadorVO to set.
	 */
	public void setListaOperadorVO(List listaOperadorVO) {
		this.listaOperadorVO = listaOperadorVO;
	}
	/**
	 * @return Returns the listaTipoOperadorVO.
	 */
	public List getListaTipoOperadorVO() {
		return listaTipoOperadorVO;
	}
	/**
	 * @param listaTipoOperadorVO The listaTipoOperadorVO to set.
	 */
	public void setListaTipoOperadorVO(List listaTipoOperadorVO) {
		this.listaTipoOperadorVO = listaTipoOperadorVO;
	}
		
}	

