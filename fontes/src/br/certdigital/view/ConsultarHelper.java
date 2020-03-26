package br.certdigital.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.certdigital.shared.util.DataFormatter;
import br.certdigital.vo.CertificadoVO;
import br.certdigital.vo.FiltroConsultaVO;
import br.certdigital.vo.ItemConsultaVO;

/**
 * View helper para consultas
 */
public class ConsultarHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private FiltroConsultaVO filtroConsultaVO;
	private ItemConsultaVO itemConsultaVO;
	private List listaItemConsultaVO;
	private int mes;
	private int ano;	

	public ConsultarHelper() {
		if (filtroConsultaVO == null) {
			filtroConsultaVO = new FiltroConsultaVO();			
		}
		if (itemConsultaVO == null) {
			itemConsultaVO = new ItemConsultaVO();			
		}
		if (listaItemConsultaVO == null) {
			listaItemConsultaVO = new ArrayList();
		}		
	}

	public FiltroConsultaVO getFiltroConsultaVO() {
		return filtroConsultaVO;
	}

	public void setFiltroConsultaVO(FiltroConsultaVO filtroConsultaVO) {
		this.filtroConsultaVO = filtroConsultaVO;
	}

	public ItemConsultaVO getItemConsultaVO() {
		return itemConsultaVO;
	}

	public void setItemConsultaVO(ItemConsultaVO itemConsultaVO) {
		this.itemConsultaVO = itemConsultaVO;
	}

	public List getListaItemConsultaVO() {
		return listaItemConsultaVO;
	}

	public void setListaItemConsultaVO(List listaItemConsultaVO) {
		this.listaItemConsultaVO = listaItemConsultaVO;
	}
	
	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	/**
	 * Efetua o parse dos campos de string para o tipo original
	 */
	public void parseFields() {
		if (itemConsultaVO != null) {
			try {
				if (itemConsultaVO.getDocumentoAsString() != null) {
					itemConsultaVO.setDocumento(DataFormatter.parseLong(itemConsultaVO.getDocumentoAsString()));
				}
				if (itemConsultaVO.getDataEmissaoAsString() != null) {
					itemConsultaVO.setDataEmissao(DataFormatter.parseShortTimestamp(itemConsultaVO.getDataEmissaoAsString()));
				}
				if (itemConsultaVO.getCustoEntidadeAsString() != null) {
					itemConsultaVO.setCustoEntidade(DataFormatter.parseDouble(itemConsultaVO.getCustoEntidadeAsString()));
				}
				if (itemConsultaVO.getCustoSugeridoAsString() != null) {
					itemConsultaVO.setCustoSugerido(DataFormatter.parseDouble(itemConsultaVO.getCustoSugeridoAsString()));
				}
				
			} catch (ParseException e){}			
		}
	}

	/**
	 * Formata os campos do vo para exibir na tela
	 */
	public void formatFields() {
		double porcentagem=0;
		double valor=0;
		double resultado=0;
		resultado = valor + (valor*porcentagem)/100;
		
		if (listaItemConsultaVO != null && listaItemConsultaVO.size() > 0) {
			Iterator it = listaItemConsultaVO.iterator();
			Object vo = null;
			while (it.hasNext()) {
				vo = it.next();

				if (((ItemConsultaVO) vo).getDocumento() != null) {
					if(((ItemConsultaVO) vo).getTipo_pessoa().equals("F")) {
						((ItemConsultaVO) vo).setDocumentoAsString(DataFormatter.formatCPF(((ItemConsultaVO) vo).getDocumento().toString()));
					}else {
						((ItemConsultaVO) vo).setDocumentoAsString(DataFormatter.formatCNPJ(((ItemConsultaVO) vo).getDocumento().toString()));
					}
				}
				if (((ItemConsultaVO) vo).getDataEmissao() != null) {
					try {
						((ItemConsultaVO) vo).setDataEmissaoAsString(DataFormatter.formatShortDate(((ItemConsultaVO) vo).getDataEmissao()));
					} catch (ParseException e) {}
				}
				if(((ItemConsultaVO) vo).getCustoEntidade() != null) {
					try {
						((ItemConsultaVO) vo).setCustoEntidadeAsString(DataFormatter.formatDouble(((ItemConsultaVO) vo).getCustoEntidade()));
					} catch (ParseException e) {}
				}
				if (((ItemConsultaVO) vo).getCustoSugerido() != null) {
					try {
						if(((ItemConsultaVO) vo).getAssociado().equals("S") && ((ItemConsultaVO) vo).getPercentual()!=null) {
							Double num = (((ItemConsultaVO) vo).getCustoSugerido());
							porcentagem = (((ItemConsultaVO) vo).getPercentual()).doubleValue();
							valor = num.doubleValue();
							resultado = valor - (valor*porcentagem)/100;
							((ItemConsultaVO) vo).setCustoSugerido(Double.valueOf(resultado));
						}
						((ItemConsultaVO) vo).setCustoSugeridoAsString(DataFormatter.formatDouble(((ItemConsultaVO) vo).getCustoSugerido()));
					} catch (ParseException e) {}
				}
				
			}
		}
		
		if (itemConsultaVO != null) {
			if (itemConsultaVO.getDocumento() != null) {
				if(itemConsultaVO.getTipo_pessoa().equals("F")) {
					itemConsultaVO.setDocumentoAsString(DataFormatter.formatCPF(itemConsultaVO.getDocumento().toString()));
				}else {
					itemConsultaVO.setDocumentoAsString(DataFormatter.formatCNPJ(itemConsultaVO.getDocumento().toString()));
				}
			}
			if (itemConsultaVO.getDataEmissao() != null) {
				try {
					itemConsultaVO.setDataEmissaoAsString(DataFormatter.formatShortDate(itemConsultaVO.getDataEmissao()));
				} catch (ParseException e) {}
			}
			if(itemConsultaVO.getCustoEntidade() != null) {
				try {
					itemConsultaVO.setCustoEntidadeAsString(DataFormatter.formatDouble(itemConsultaVO.getCustoEntidade()));
				} catch (ParseException e) {}
			}
			if (itemConsultaVO.getCustoSugerido() != null) {
				try {
					itemConsultaVO.setCustoSugeridoAsString(DataFormatter.formatDouble(itemConsultaVO.getCustoSugerido()));
				} catch (ParseException e) {}
			}
			
		}
		
	}
	
}
