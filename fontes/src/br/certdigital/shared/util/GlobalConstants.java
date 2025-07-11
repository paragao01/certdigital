package br.certdigital.shared.util;

public class GlobalConstants {

	/* Banco POSTGRE */
	public static String USR = "certdig";
	public static String DRV = "org.postgresql.Driver";
	
	//Ambiente de Desenvolvimento
    public static String URL = "jdbc:postgresql://certdigital-db:5432/certdigital";	//Acesso Postgree
	public static String PWR = "certdig!1234";									//Acesso Postgree
	
	/*/Ambiente de Producao
	public static String URL = "jdbc:postgresql://10.1.1.19:5432/certificado";	//Acesso Postgree
	public static String PWR = "certdig!1234";                     				//Acesso Postgree
	*/
	
    /* Caminho do data source */
    public static String JNDI_DS = "java:/comp/env/GARANTIADB";
    
    //nome do objeto do usuario colocado na sessao
    public static String OPERADOR_INFO = "OPERADOR_INFO";

    // pagina de login
    public static String LOGIN_PAGE = "/login.jsp";

    public static String LOGIN_PAGE_DO = "/login.do";

    // pagina de acesso negado
    public static String ACCESS_DENIED_PAGE = "/accessDenied.jsp";

    public static String LOGOUT_PAGE = "login";

    //Quantidade de cheques consultados na garantia
    public static Long QTDE_CHEQUES_CONSULTA = new Long(15);
        
    // FLAG SIM/NAO
    public static String VALOR_FLAG_SIM = "S";
    public static String VALOR_FLAG_NAO = "N";
    public static String LABEL_FLAG_SIM = "Sim";
    public static String LABEL_FLAG_NAO = "Nao";

    public static Long NULL_LONG = new Long(0);

    // TIPO DE PESSOA
    public static String VALOR_FISICA = "F";
    public static String VALOR_JURIDICA = "J";
    public static String LABEL_FISICA = "Fisica";
    public static String LABEL_JURIDICA = "Juridica";
    
    // STATUS DO OPERADOR
    public static String STATUS_OPERADOR_BLOQUEADO           = "B";
    public static String DESCRICAO_STATUS_OPERADOR_BLOQUEADO = "Bloqueado";
    public static String STATUS_OPERADOR_INATIVO             = "I";
    public static String DESCRICAO_STATUS_OPERADOR_INATIVO   = "Inativo";
    public static String STATUS_OPERADOR_ATIVO               = "A";
    public static String DESCRICAO_STATUS_OPERADOR_ATIVO     = "Ativo";

    // TIPO DE OPERADOR
    public static Long OPERADOR_MASTER = new Long(1);    
    public static Long OPERADOR_EMPRESA = new Long(2);
    public static Long OPERADOR_ADMINISTRATIVO = new Long(3);
            
}