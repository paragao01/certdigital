package br.certdigital.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.certdigital.vo.ProdutoVO;

public class ProdutoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ProdutoVO produtoVO;
	private List listaProdutoVO;
		
	public ProdutoHelper(){		
		produtoVO = new ProdutoVO();
		listaProdutoVO = Collections.EMPTY_LIST;
	}

	public ProdutoVO getProdutoVO() {
		return produtoVO;
	}

	public void setProdutoVO(ProdutoVO produtoVO) {
		this.produtoVO = produtoVO;
	}

	public List getListaProdutoVO() {
		return listaProdutoVO;
	}

	public void setListaProdutoVO(List listaProdutoVO) {
		this.listaProdutoVO = listaProdutoVO;
	}
	
}	

