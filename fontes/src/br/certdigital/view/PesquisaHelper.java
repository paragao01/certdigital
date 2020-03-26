package br.certdigital.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.certdigital.shared.util.DataFormatter;
import br.certdigital.vo.FiltroConsultaVO;
import br.certdigital.vo.ItemPesquisaVO;

/**
 * View helper para consultas
 */
public class PesquisaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private FiltroConsultaVO filtroConsultaVO;
	private ItemPesquisaVO itemPesquisaVO;
	private List listaItemPesquisaVO;
	private String labelJanela;
	private String metodoPesquisaExecutar;
	private String campoCodigo;
	private String campoNome;
	private String chavePesquisa;
	private String valorRestricaoOpcional;
	private String labelValorRestricaoOpcional; 	
	
	public PesquisaHelper() {
		if (filtroConsultaVO == null) {
			filtroConsultaVO = new FiltroConsultaVO();			
		}
		if (itemPesquisaVO == null) {
			itemPesquisaVO = new ItemPesquisaVO();			
		}
		if (listaItemPesquisaVO == null) {
			listaItemPesquisaVO = new ArrayList();
		}		
	}
	
	/**
	 * @return Returns the itemPesquisaVO.
	 */
	public ItemPesquisaVO getItemPesquisaVO() {
		return itemPesquisaVO;
	}
	/**
	 * @param itemPesquisaVO The itemPesquisaVO to set.
	 */
	public void setItemPesquisaVO(ItemPesquisaVO itemPesquisaVO) {
		this.itemPesquisaVO = itemPesquisaVO;
	}
	/**
	 * @return Returns the listaItemPesquisaVO.
	 */
	public List getListaItemPesquisaVO() {
		return listaItemPesquisaVO;
	}
	/**
	 * @param listaItemPesquisaVO The listaItemPesquisaVO to set.
	 */
	public void setListaItemPesquisaVO(List listaItemPesquisaVO) {
		this.listaItemPesquisaVO = listaItemPesquisaVO;
	}
	/**
	 * @return Returns the campoCodigo.
	 */
	public String getCampoCodigo() {
		return campoCodigo;
	}
	/**
	 * @param campoCodigo The campoCodigo to set.
	 */
	public void setCampoCodigo(String campoCodigo) {
		this.campoCodigo = campoCodigo;
	}
	/**
	 * @return Returns the campoNome.
	 */
	public String getCampoNome() {
		return campoNome;
	}
	/**
	 * @param campoNome The campoNome to set.
	 */
	public void setCampoNome(String campoNome) {
		this.campoNome = campoNome;
	}
	/**
	 * @return Returns the metodoPesquisaExecutar.
	 */
	public String getMetodoPesquisaExecutar() {
		return metodoPesquisaExecutar;
	}
	/**
	 * @param metodoPesquisaExecutar The metodoPesquisaExecutar to set.
	 */
	public void setMetodoPesquisaExecutar(String metodoPesquisaExecutar) {
		this.metodoPesquisaExecutar = metodoPesquisaExecutar;
	}
	/**
	 * @return Returns the chavePesquisa.
	 */
	public String getChavePesquisa() {
		return chavePesquisa;
	}
	/**
	 * @param chavePesquisa The chavePesquisa to set.
	 */
	public void setChavePesquisa(String chavePesquisa) {
		this.chavePesquisa = chavePesquisa;
	}
	/**
	 * @return Returns the filtroConsultaVO.
	 */
	public FiltroConsultaVO getFiltroConsultaVO() {
		return filtroConsultaVO;
	}
	/**
	 * @param filtroConsultaVO The filtroConsultaVO to set.
	 */
	public void setFiltroConsultaVO(FiltroConsultaVO filtroConsultaVO) {
		this.filtroConsultaVO = filtroConsultaVO;
	}
	
	public void parseFields() {
		if (filtroConsultaVO != null) {
			try{
				if (filtroConsultaVO.getDocumentoAsString() != null) {
					filtroConsultaVO.setDocumento(DataFormatter.parseLong(filtroConsultaVO.getDocumentoAsString()));
				}
				if (filtroConsultaVO.getDataInicialAsString() != null) {
					filtroConsultaVO.setDataInicial(DataFormatter.parseShortTimestamp(filtroConsultaVO.getDataInicialAsString()));
				}
				if (filtroConsultaVO.getDataFinalAsString() != null) {
					filtroConsultaVO.setDataFinal(DataFormatter.parseShortTimestamp(filtroConsultaVO.getDataFinalAsString()));
				}
			} catch (ParseException e) {}
		}
	}
	
	/**
	 * @return Returns the valorRestricaoOpcional.
	 */
	public String getValorRestricaoOpcional() {
		return valorRestricaoOpcional;
	}
	/**
	 * @param valorRestricaoOpcional The valorRestricaoOpcional to set.
	 */
	public void setValorRestricaoOpcional(String valorRestricaoOpcional) {
		this.valorRestricaoOpcional = valorRestricaoOpcional;
	}
	/**
	 * @return the labelValorRestricaoOpcional
	 */
	public String getLabelValorRestricaoOpcional() {
		return labelValorRestricaoOpcional;
	}
	/**
	 * @param labelValorRestricaoOpcional the labelValorRestricaoOpcional to set
	 */
	public void setLabelValorRestricaoOpcional(String labelValorRestricaoOpcional) {
		this.labelValorRestricaoOpcional = labelValorRestricaoOpcional;
	}

	/**
	 * @return Returns the labelJanela.
	 */
	public String getLabelJanela() {
		return labelJanela;
	}
	/**
	 * @param labelJanela The labelJanela to set.
	 */
	public void setLabelJanela(String labelJanela) {
		this.labelJanela = labelJanela;
	}
}
