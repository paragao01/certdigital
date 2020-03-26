package br.certdigital.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.certdigital.shared.util.DataFormatter;
import br.certdigital.vo.CertificadoVO;

public class CertificadoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private CertificadoVO certificadoVO;
	private List listaCertificadoVO;
	
	public CertificadoHelper() {
		if (certificadoVO == null) {
			certificadoVO = new CertificadoVO();
		}
		if (listaCertificadoVO == null) {
			listaCertificadoVO = Collections.EMPTY_LIST;
		}
	}
	
	public CertificadoVO getCertificadoVO() {
		return certificadoVO;
	}


	public void setCertificadoVO(CertificadoVO certificadoVO) {
		this.certificadoVO = certificadoVO;
	}


	public List getListaCertificadoVO() {
		return listaCertificadoVO;
	}


	public void setListaCertificadoVO(List listaCertificadoVO) {
		this.listaCertificadoVO = listaCertificadoVO;
	}

	/**
	 * Efetua o parse dos campos de string para o tipo original
	 */
	public void parseFields() {
		if (certificadoVO != null) {
			try {
				if (certificadoVO.getDocumentoAsString() != null) {
					certificadoVO.setDocumento(DataFormatter.parseLong(certificadoVO.getDocumentoAsString()));
				}
				if (certificadoVO.getCpfResponsavelAsString() != null) {
					certificadoVO.setCpfResponsavel(DataFormatter.parseLong(certificadoVO.getCpfResponsavelAsString()));
				}				
				if (certificadoVO.getCepResponsavelAsString() != null) {
					certificadoVO.setCepResponsavel(DataFormatter.parseLong(certificadoVO.getCepResponsavelAsString()));
				}
				if (certificadoVO.getDataNascResponsavelAsString() != null) {
					certificadoVO.setDataNascResponsavel(DataFormatter.parseShortTimestamp(certificadoVO.getDataNascResponsavelAsString()));
				}
				if (certificadoVO.getDataEmissaoAsString() != null) {
					certificadoVO.setDataEmissao(DataFormatter.parseShortTimestamp(certificadoVO.getDataEmissaoAsString()));
				}
				
			} catch (ParseException e){}			
		}
	}

	/**
	 * Formata os campos do vo para exibir na tela
	 */
	public void formatFields() {
		if (listaCertificadoVO != null && listaCertificadoVO.size() > 0) {
			Iterator it = listaCertificadoVO.iterator();
			Object vo = null;
			while (it.hasNext()) {
				vo = it.next();

				if (((CertificadoVO) vo).getDocumento() != null && ((CertificadoVO) vo).getTipo_pessoa() != null) {
					if(((CertificadoVO) vo).getTipo_pessoa().equals("F")) {
						((CertificadoVO) vo).setDocumentoAsString(DataFormatter.formatCPF(((CertificadoVO) vo).getDocumento().toString()));
					}else {
						((CertificadoVO) vo).setDocumentoAsString(DataFormatter.formatCNPJ(((CertificadoVO) vo).getDocumento().toString()));
					}
				}
				if (((CertificadoVO) vo).getCpfResponsavel() != null) {
					((CertificadoVO) vo).setCpfResponsavelAsString(DataFormatter.formatCPF(((CertificadoVO) vo).getCpfResponsavel().toString()));
				}			
				if (((CertificadoVO) vo).getCepResponsavel() != null) {
					((CertificadoVO) vo).setCepResponsavelAsString(DataFormatter.formatCEP(((CertificadoVO) vo).getCepResponsavel().longValue()));
				}
				if (((CertificadoVO) vo).getDataNascResponsavel() != null) {
					try {
						((CertificadoVO) vo).setDataNascResponsavelAsString(DataFormatter.formatShortDate(((CertificadoVO) vo).getDataNascResponsavel()));
					} catch (ParseException e) {}
				}	
				if (((CertificadoVO) vo).getDataEmissao() != null) {
					try {
						((CertificadoVO) vo).setDataEmissaoAsString(DataFormatter.formatShortDate(((CertificadoVO) vo).getDataEmissao()));
					} catch (ParseException e) {}
				}				
			}
		}
		
		if (certificadoVO != null) {
			if (certificadoVO.getDocumento() != null && certificadoVO.getTipo_pessoa() != null) {
				if(certificadoVO.getTipo_pessoa().equals("F")) {
					certificadoVO.setDocumentoAsString(DataFormatter.formatCPF(certificadoVO.getDocumento().toString()));
				}else {
					certificadoVO.setDocumentoAsString(DataFormatter.formatCNPJ(certificadoVO.getDocumento().toString()));
				}
			}
			if (certificadoVO.getCpfResponsavel() != null) {
				certificadoVO.setCpfResponsavelAsString(DataFormatter.formatCPF(certificadoVO.getCpfResponsavel().toString()));
			}			
			if (certificadoVO.getCepResponsavel() != null) {
				certificadoVO.setCepResponsavelAsString(DataFormatter.formatCEP(certificadoVO.getCepResponsavel().longValue()));
			}
			if (certificadoVO.getDataNascResponsavel() != null) {
				try {
					certificadoVO.setDataNascResponsavelAsString(DataFormatter.formatShortDate(certificadoVO.getDataNascResponsavel()));
				} catch (ParseException e) {}
			}	
			if (certificadoVO.getDataEmissao() != null) {
				try {
					certificadoVO.setDataEmissaoAsString(DataFormatter.formatShortDate(certificadoVO.getDataEmissao()));
				} catch (ParseException e) {}
			}						
		}
	}
	
}
