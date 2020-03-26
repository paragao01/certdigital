package br.certdigital.view;

import java.io.Serializable;

import br.certdigital.vo.EntidadeVO;
import br.certdigital.vo.ItemEntidadeVO;
import br.certdigital.vo.OperadorVO;

public class EntidadeHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EntidadeVO entidadeVO;
	private ItemEntidadeVO itemEntidadeVO;
	
	public EntidadeHelper(){
		entidadeVO = new EntidadeVO();
	}

	public EntidadeVO getEntidadeVO() {
		return entidadeVO;
	}

	public void setEntidadeVO(EntidadeVO entidadeVO) {
		this.entidadeVO = entidadeVO;
	}

	public ItemEntidadeVO getItemEntidadeVO() {
		return itemEntidadeVO;
	}


	public void setItemEntidadeVO(ItemEntidadeVO itemEntidadeVO) {
		this.itemEntidadeVO = itemEntidadeVO;
	}

	public void setParamItemEntidadeVO(ItemEntidadeVO itemVO, OperadorVO operadorVO) {
		itemVO.setOperadorAlteracao(operadorVO.getIdOperador());
		itemVO.setOperadorInlcusao(operadorVO.getIdOperador());
	}
	
}
