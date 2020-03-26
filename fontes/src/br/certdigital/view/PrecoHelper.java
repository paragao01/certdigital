package br.certdigital.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.certdigital.shared.util.DataFormatter;
import br.certdigital.vo.PrecoVO;

public class PrecoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private PrecoVO precoVO;
	
	private List listaPrecoVO;
		
	public PrecoHelper(){		
		precoVO = new PrecoVO();
		listaPrecoVO = Collections.EMPTY_LIST;
	}

	public PrecoVO getPrecoVO() {
		return precoVO;
	}

	public void setPrecoVO(PrecoVO precoVO) {
		this.precoVO = precoVO;
	}

	public List getListaPrecoVO() {
		return listaPrecoVO;
	}

	public void setListaPrecoVO(List listaPrecoVO) {
		this.listaPrecoVO = listaPrecoVO;
	}
	
	public void parseFields() {
		try {
			if (precoVO.getPrecoEntidadeAsString() != null) {
				precoVO.setPrecoEntidade(DataFormatter.parseDouble(precoVO.getPrecoEntidadeAsString()));
			}
			if (precoVO.getPrecoSugeridoAsString() != null) {
				precoVO.setPrecoSugerido(DataFormatter.parseDouble(precoVO.getPrecoSugeridoAsString()));
			}
			if (precoVO.getDataReferenciaAsString() != null) {
				precoVO.setDataReferencia(DataFormatter.parseShortTimestamp(precoVO.getDataReferenciaAsString()));
			}
		} catch (ParseException e) {}
	}
	
	public void formatFields() {
		if (listaPrecoVO != null && listaPrecoVO.size() > 0) {
			Iterator it = listaPrecoVO.iterator();
			Object vo = null;
			while (it.hasNext()) {
				vo = it.next();

				if(((PrecoVO) vo).getPrecoEntidade() != null) {
					try {
						((PrecoVO) vo).setPrecoEntidadeAsString(DataFormatter.formatDouble(((PrecoVO) vo).getPrecoEntidade()));
					} catch (ParseException e) {}
				}
				if (((PrecoVO) vo).getPrecoSugerido() != null) {
					try {
						((PrecoVO) vo).setPrecoSugeridoAsString(DataFormatter.formatDouble(((PrecoVO) vo).getPrecoSugerido()));
					} catch (ParseException e) {}
				}
				if (((PrecoVO) vo).getDataReferencia() != null) {
					try {
						((PrecoVO) vo).setDataReferenciaAsString(DataFormatter.formatShortDate(((PrecoVO) vo).getDataReferencia()) );
					} catch (ParseException e) {}
				}
			}
		}
		if(precoVO.getPrecoEntidade() != null) {
			try {
				precoVO.setPrecoEntidadeAsString(DataFormatter.formatDouble(precoVO.getPrecoEntidade()));
			} catch (ParseException e) {}
		}
		if (precoVO.getPrecoSugerido() != null) {
			try {
				precoVO.setPrecoSugeridoAsString(DataFormatter.formatDouble(precoVO.getPrecoSugerido()));
			} catch (ParseException e) {}
		}
		if (precoVO.getDataReferencia() != null) {
			try {
				precoVO.setDataReferenciaAsString(DataFormatter.formatShortDate(precoVO.getDataReferencia()) );
			} catch (ParseException e) {}
		}

	}
}	

