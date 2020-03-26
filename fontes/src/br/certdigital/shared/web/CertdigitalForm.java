package br.certdigital.shared.web;

import br.certdigital.view.*;

/**
 *  Action Form do Sistema
 *
 */
public class CertdigitalForm extends BaseForm {
	
	private static final long serialVersionUID = 1L;
	
    private LoginHelper loginHelper;
    private PesquisaHelper pesquisaHelper;
    private EntidadeHelper entidadeHelper;
    private EmpresaHelper empresaHelper;
    private EstadoHelper estadoHelper;
    private ProdutoHelper produtoHelper;
    private PrecoHelper precoHelper;
    private CertificadoHelper certificadoHelper;
    private ConsultarHelper consultarHelper;

    /**
     *  Default Constructor
     */
    public CertdigitalForm() {
        loginHelper = new LoginHelper();
        pesquisaHelper = new PesquisaHelper();
        entidadeHelper = new EntidadeHelper();
        empresaHelper = new EmpresaHelper();
        estadoHelper = new EstadoHelper();
        produtoHelper = new ProdutoHelper();
        precoHelper = new PrecoHelper();
        certificadoHelper = new CertificadoHelper();
        consultarHelper = new ConsultarHelper();
    }

	public LoginHelper getLoginHelper() {
		return loginHelper;
	}

	public void setLoginHelper(LoginHelper loginHelper) {
		this.loginHelper = loginHelper;
	}

	public PesquisaHelper getPesquisaHelper() {
		return pesquisaHelper;
	}

	public void setPesquisaHelper(PesquisaHelper pesquisaHelper) {
		this.pesquisaHelper = pesquisaHelper;
	}


	public EntidadeHelper getEntidadeHelper() {
		return entidadeHelper;
	}

	public void setEntidadeHelper(EntidadeHelper entidadeHelper) {
		this.entidadeHelper = entidadeHelper;
	}


	public EmpresaHelper getEmpresaHelper() {
		return empresaHelper;
	}

	public void setEmpresaHelper(EmpresaHelper empresaHelper) {
		this.empresaHelper = empresaHelper;
	}

	public EstadoHelper getEstadoHelper() {
		return estadoHelper;
	}

	public void setEstadoHelper(EstadoHelper estadoHelper) {
		this.estadoHelper = estadoHelper;
	}

	public ProdutoHelper getProdutoHelper() {
		return produtoHelper;
	}

	public void setProdutoHelper(ProdutoHelper produtoHelper) {
		this.produtoHelper = produtoHelper;
	}


	public PrecoHelper getPrecoHelper() {
		return precoHelper;
	}

	public void setPrecoHelper(PrecoHelper precoHelper) {
		this.precoHelper = precoHelper;
	}

	public CertificadoHelper getCertificadoHelper() {
		return certificadoHelper;
	}

	public void setCertificadoHelper(CertificadoHelper certificadoHelper) {
		this.certificadoHelper = certificadoHelper;
	}

	public ConsultarHelper getConsultarHelper() {
		return consultarHelper;
	}

	public void setConsultarHelper(ConsultarHelper consultarHelper) {
		this.consultarHelper = consultarHelper;
	}
	
}
