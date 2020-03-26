package br.certdigital.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import br.certdigital.shared.util.DataFormatter;
import br.certdigital.vo.EmpresaVO;
import br.certdigital.vo.OperadorVO;

/**
 * View Helper para o cadastro de empresa
 * 
 * @author elisio
 */
public class EmpresaHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EmpresaVO empresaVO;
	private List listaEmpresaVO;
	
	public EmpresaHelper() {
		if (empresaVO == null) {
			empresaVO = new EmpresaVO();
		}
		if (listaEmpresaVO == null) {
			listaEmpresaVO = Collections.EMPTY_LIST;
		}
	}
	
	/**
	 * @return Returns the empresaVO.
	 */
	public EmpresaVO getEmpresaVO() {
		return empresaVO;
	}
	
	/**
	 * @param empresaVO The empresaVO to set.
	 */
	public void setEmpresaVO(EmpresaVO empresaVO) {
		this.empresaVO = empresaVO;
	}
	
	/**
	 * @return Returns the listaEmpresaVO.
	 */
	public List getListaEmpresaVO() {
		return listaEmpresaVO;
	}
	
	/**
	 * @param listaEmpresaVO The listaEmpresaVO to set.
	 */
	public void setListaEmpresaVO(List listaEmpresaVO) {
		this.listaEmpresaVO = listaEmpresaVO;
	}
	
	/**
	 * Formata os campos do vo para exibir na tela
	 */
	public void formatFields() {
		if (empresaVO != null) {
			if (empresaVO.getNumCNPJ() != null) {
				empresaVO.setNumCNPJAsString(DataFormatter.formatCNPJZerosLeft(empresaVO.getNumCNPJ().longValue()));
			}
			if (empresaVO.getCepComercial() != null) {
				empresaVO.setCepComercialAsString(DataFormatter.formatCEP(empresaVO.getCepComercial().longValue()));
			}
			if (empresaVO.getCepCobranca() != null) {
				empresaVO.setCepCobrancaAsString(DataFormatter.formatCEP(empresaVO.getCepCobranca().longValue()));
			}
		}
	}
	
	/**
	 * Efetua o parse dos campos de string para o tipo original
	 */
	public void parseFields() {
		if (empresaVO != null) {
			try {
				if (empresaVO.getNumCNPJAsString() != null) {
					empresaVO.setNumCNPJ(DataFormatter.parseLong(empresaVO.getNumCNPJAsString()));
				}
				if (empresaVO.getCepComercialAsString() != null) {
					empresaVO.setCepComercial(DataFormatter.parseLong(empresaVO.getCepComercialAsString()));
				}
				if (empresaVO.getCepCobrancaAsString() != null) {
					empresaVO.setCepCobranca(DataFormatter.parseLong(empresaVO.getCepCobrancaAsString()));
				}
			} catch (ParseException e){}			
		}
	}

	public void setParamEmpresaVO(EmpresaVO itemVO, OperadorVO operadorVO) {
		itemVO.setOperadorInc(operadorVO.getIdOperador());
		itemVO.setOperadorAlt(operadorVO.getIdOperador());
		//itemVO.setIdUnidadeNegocio(operadorVO.getIdUnidadeNegocio());
		//itemVO.setIdGestor(operadorVO.getIdGestor());
	}

}
