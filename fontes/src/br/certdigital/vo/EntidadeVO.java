package br.certdigital.vo;

import java.util.List;

public class EntidadeVO {
	private ItemEntidadeVO itemEntidadeVO;
	private List listaItemEntidadeVO;
	private List listaEstadoVO;
	
	public EntidadeVO(){
		if(itemEntidadeVO==null){
			itemEntidadeVO = new ItemEntidadeVO();
	    }
	}
	
	/**
	 * @return Returns the itemEntidadeVO.
	 */
	public ItemEntidadeVO getItemEntidadeVO() {
		return itemEntidadeVO;
	}
	/**
	 * @param itemEntidadeVO The itemEntidadeVO to set.
	 */
	public void setItemEntidadeVO(ItemEntidadeVO itemEntidadeVO) {
		this.itemEntidadeVO = itemEntidadeVO;
	}
	/**
	 * @return Returns the listaItemEntidadeVO.
	 */
	public List getListaItemEntidadeVO() {
		return listaItemEntidadeVO;
	}
	/**
	 * @param listaItemEntidadeVO The listaItemEntidadeVO to set.
	 */
	public void setListaItemEntidadeVO(List listaItemEntidadeVO) {
		this.listaItemEntidadeVO = listaItemEntidadeVO;
	}
	/**
	 * @return Returns the listaEstadoVO.
	 */
	public List getListaEstadoVO() {
		return listaEstadoVO;
	}
	/**
	 * @param listaEstadoVO The listaEstadoVO to set.
	 */
	public void setListaEstadoVO(List listaEstadoVO) {
		this.listaEstadoVO = listaEstadoVO;
	}
}
