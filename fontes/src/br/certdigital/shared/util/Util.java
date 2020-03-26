package br.certdigital.shared.util;

public class Util extends Formatador{
	
	/**
	 * Separa as respostas no retorno da consulta
	 * @param strRet
	 * @return Collection
	 */
	
	/**
	 * Verifica se a string esta branco
	 * @param str
	 * @return boolean
	 */
	public static boolean Empty(String str){
		if(str.trim().length()==0) return true;
		return false;
	}
	
	/**
	 * Verifica se str1 esta contida em str
	 * @param str
	 * @param str1
	 * @return boolean
	 */
	public static boolean strstr(String str, String str1){
		if(str.indexOf(str1)>=0)return true;
		return false;
	}
	
	
	public static String copyStr (String src, String trg, int pos){
		int tamSrc    = src.length();
		int tamTrg    = trg.length();
		String trgAnt = trg.substring(0, pos);
		int tamAnt    = trgAnt.length();
		String trgPos = trg.substring(tamAnt+tamSrc,tamTrg);
		
		return (trgAnt+src+trgPos);
	}
	
	public static boolean isnumeric(String str){
		boolean valor = false;
		int i;		        
		int tam;
		
		tam = str.length();
		if (tam == 0)
			return false;

		str.replace(",", "."); //Converte a virgula em ponto.

		for (i=0;i < tam; i++) {
			if(strstr(String.valueOf(str.charAt(i)), ".")){ 
				if (valor==false)
					return false;
			}
			else
				if (isdigit(String.valueOf(str.charAt(i))))
					valor=true;
				else
					return true;
		}
		return valor;
	}
	
	public static boolean isdigit(String val){
		try {  
			int num = Integer.parseInt(val);
			if(num==0){} //So para tirar o warn;
			return true;
		} catch( NumberFormatException ex ) {
			return false;
		} catch( Exception exc ){
			return false;
		}
	}
	
	public static boolean IsZero (String str) {
		str = str.trim();
		for (int i = 0; i < str.length(); i++) {
			if(!strstr(String.valueOf(str.charAt(i)), "0"))
                return false;
			
		}
		return true;
	}
	
	public static int val (String str, int inicio, int tam){
        String valor = "";
        String c;
        int inteiro;
        int i;
        
		try {
	        zeroEsquerda(valor, tam);

	        for (i=0;i < tam;i++) {
	        	c = String.valueOf(str.charAt(inicio+i));
	        	if (!isdigit(c))
	        		break;

	        	valor += c; // Obtem os numeros.
	        }
			
			inteiro = Integer.parseInt(valor);
			return inteiro;
		} catch( NumberFormatException ex ) {
			return 0;
		} catch( Exception exc ){
			return 0;
		}        
	}

	public static boolean IsCPF (String CPF){
		int i;int j;int d;

		if (CPF.length() == 0) return false;

		if (IsZero(CPF)) return false;

		unformatString(CPF);

		if (!isnumeric(CPF)) return false;

		if (CPF.length() > 11) return false;

		Util.zeroEsquerda(CPF, 11);

		for (i = 10; i <= 11; i++) {
			d = 0;
			for (j = i; j >= 2; j--)
				d += val(CPF, i-j, 1) * j;

			d = (d %= 11) < 2 ? 0 : 11 - d;
			if (val(CPF, i-1, 1) != d)
				return false;
		}

		return true;
	}
	
	public static boolean IsCNPJ (String str){
		int i;int j;int d;

		if (str.length() == 0) return false;

		if (IsZero(str)) return false;

		unformatString(str);

		if (!isnumeric(str)) return false;

		if (str.length() != 14)	return false;

		for (i = 13; i <= 14; i++) {
			d = 0;
			for (j = i; j >= 2; j--)
				d += val(str, i-j, 1) * ((j-2) % 8 + 2);

			d = (d %= 11) < 2 ? 0 : 11 - d;
			if (val(str, i-1, 1) != d)
				return false;
		}
		
		return true;
	}

	public static String pesquisaNaturezaOperacao(String codigo) {
		int i;		
		String descricao;
		String[] tabela = {"01CREDITO DIRETO AO CONSUMIDOR (COMPRA DE BENS)",
						   "02CHEQUE COBR. DEVOLV",
						   "03LOCADORA",
						   "04CONSÓRCIO",
						   "05IMOBILIÁRIA ADMINISTRAÇÃO DE BENS",
						   "06CREDITO IMOBILIÁRIO",
						   "07OUTRAS AT. ECONOMICAS",
						   "08NÃO GRAVAR PASSAGEM",
						   "09CREDITO DE VEÍCULO",
						   "10CRÉDITO PESSOAL",
						   "11TÍTULO PROTESTADO",
						   "12REGISTROS",
						   "13NÃO INFORMADO",
						   "14CARTÃO DE CRÉDITO",
						   "15OP COM TELECOMUNICACOES",
						   "99OUTROS TIPOS"
						   };

		descricao = " NATUREZA NAO ENCONTRADA";
		for (i=0; i < tabela.length; i++){
			if (tabela[i].substring(0, 2).equals(codigo)) {
				descricao = tabela [i].substring(2, tabela[i].length());
				break;
			}
		}
		
		return descricao;
	}
	
}