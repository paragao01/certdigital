package br.certdigital.shared.util;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;

import br.certdigital.tools.util.Formatter;

public class DataFormatter
{
  public static String getProperty(String id)
  {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    return resource.getString(id);  
  }
  
  public static Long zeroToNull(Long num)
  {
    if (num!=null && num.longValue()==0L) return null;
    else return num;
  }
  /**
	 * Tira os zeros  esquerda de uma string que representa um nmero inteiro.
	 *
	 * @param str O string da qual ser retirada os zeros  esquerda.
	 *
	 * @return O string sem zeros  esquerda.
	 * 
	 * Aragao
	 */
  public static String retiraZerosEsquerda(String str) {
	  BigInteger i = new BigInteger(str.trim());
	  return i.toString();
  }

  public static String getElementValue(String element, String xmlData)
  {
    int idxIni = xmlData.indexOf("<"+element+">");
    int idxFim = xmlData.indexOf("</"+element+">");

    if (idxIni==-1 || idxFim==-1)  return null;
    return ( xmlData.substring(idxIni+("<"+element+">").length(), idxFim).trim() );
  }

  public static Long[] convertToLong(String data[]) throws ParseException
  {
    Vector dataList = new Vector();
    Long res[];

    if (data==null) return null;

    for (int i=0; i<data.length; i++)
    {
        if (data[i]==null || data[i].trim().equals("")) continue;
       dataList.add(new Long(data[i].trim()));
    }

    if (dataList.size()==0) return null;

    res = new Long[dataList.size()];
    dataList.copyInto(res);
    dataList.clear();

    return res;
  }

  public static String safeString(Object obj)
  {
    if (obj==null) return "";
    else return obj.toString();
  }

  public static Long parseLong(String num) throws ParseException
  {
	if(num != null){
		num = num.replace(".","");num = num.replace("/","");num = num.replace("-","");
	}
    String mask;
    if (num==null || num.trim().equals("")) return null;
    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    mask = resource.getString("integer.mask");

    }
    catch (Exception exc)
    {
      mask="###,###,###,###,###,##0";
    }
    return Formatter.parseLong(num, mask);

  }

  public static Double parseDouble(String num) throws ParseException
  {
    String mask;
    if (num==null || num.trim().equals("")) return null;
    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    mask = resource.getString("double.mask");

    //System.out.println("parseDouble - DoubleMask OK!");
    }
    catch (Exception exc)
    {
      mask="###,###,###,###,###,##0.00";
    }
    return Formatter.parseDouble(num, mask);

  }

  public static String formatDouble(Double num) throws ParseException
  {
    String mask;
    if (num==null) return "";
    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    mask = resource.getString("double.mask");

    //System.out.println("formatDouble - DoubleMask OK!");
    }
    catch (Exception exc)
    {
      mask="###,###,###,###,###,##0.00";
    }
    return Formatter.formatNumber(num, mask);
  }

  public static String formatLong(Long num) throws ParseException
  {
    String mask;
    if (num==null) return "";
    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    mask = resource.getString("integer.mask");
    }
    catch (Exception exc)
    {
      mask="###,###,###,###,###,##0";
    }
    return Formatter.formatNumber(num, mask);
  }


  public static String formatShortDate(java.util.Date dt) throws ParseException
  {
    //String dateHourMask="";
    String dateMask="";
    //return formatDate(dt);

    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    dateMask = resource.getString("short.date.mask");
    }
    catch (Exception e)
    {
    dateMask="dd/MM/yyyy" ;
    }
    return Formatter.formatDate(dt,dateMask);
  }

  public static String formatLongDate(java.util.Date dt) throws ParseException
  {
    //String dateHourMask="";
    String dateMask="";
    //return formatDate(dt);

    try {
    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    dateMask = resource.getString("short.datehour.mask");
    }
    catch (Exception e)
    {
    dateMask="dd/MM/yyyy HH:mm:ss" ;
    }
    return Formatter.formatDate(dt,dateMask);
  }

  public static Timestamp parseShortTimestamp(String dt) throws ParseException
  {
    String dateHourMask="";
    String dateMask="";
    //return parseTimestamp(dt);

    ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    dateHourMask = resource.getString("short.datehour.mask");
    dateMask =  resource.getString("short.date.mask");    
    Timestamp resp = null;

      // aplica o parse com a mascara de data/hora
      // OBS: Qdo nao vem a hora, ocorre erro no parse !
      try {
        resp = Formatter.parseTimestamp(dt, dateHourMask);
        }
      catch (ParseException e)
      {
          // aplica a mascara de data apenas
        resp = Formatter.parseTimestamp(dt, dateMask);
      }

      return resp;
  }

  public static Date parseDate(String dt) throws ParseException
  {
    //String dateHourMask=""; 
    //String dateMask=null;
    //return parseTimestamp(dt);

    //ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
    Date resp = null;

      // aplica o parse com a mascara de data/hora
      // OBS: Qdo nao vem a hora, ocorre erro no parse !
      try {
        resp = java.sql.Date.valueOf(dt);
        }
      catch (Exception exc)
      {
          // aplica a mascara de data apenas
          //dateMask =  resource.getString("short.date.mask");
      }

      return resp;
  }

  
  
  
  
  /**
   * Formata um numero de CPF, apenas preenchendo com zeros a esquerda do
   * numero e do digito.
   *
   * @param Long cpf    - numero do cpf
   * @param Long cpfDig - dugito do cpf
   * @return String
   */
  public static String formatCpfZerosLeft(Long cpf, Long cpfDig)
  {
    if ((cpf == null) || (cpfDig == null))
      return "";
    String strCpf    = "";
    String strCpfDig = "";
    if (cpf.toString().length() < 9)
    {
      for(int i=0;i<(9-cpf.toString().length());i++)
        strCpf += "0";
    }
    strCpf = strCpf+cpf.toString();
    if (cpfDig.toString().length() < 2)
    {
      for(int i=0;i<(2-cpfDig.toString().length());i++)
        strCpfDig += "0";
    }
    strCpfDig = strCpfDig+cpfDig.toString();
    return strCpf + strCpfDig;
  }


  /**
   * Formata um numero de CPF com a mascara
   *
   * @param Long cpf    - numero do cpf
   * @param Long cpfDig - digito do cpf
   * @return String
   */
  public static String formatCPF(Long cpf, Long cpfDig)
  {
	  
    if ((cpf == null) || (cpfDig == null))
      return "";
    String result = formatCpfZerosLeft(cpf,cpfDig);
    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cpf.mask");
    }
    catch (Exception exc)
    {
      mask="###.###.###-##";
    }
    return formatString(result,mask,true);
  }

  /**
   * Formata um numero de CPF com a mascara
   *
   * @param String numIni - numero inicial do cpf
   * @param String numFim - numero final do cpf
   * @return String
   */
  public static String formatCPF(String numIni, String numFim)
  {
    if ((numIni == null) || (numFim == null)) return "";
    
    String result = DataFormatter.fillLeft(DataFormatter.safeString(numIni), 9, '0') +
                    DataFormatter.fillLeft(DataFormatter.safeString(numFim), 2, '0');

    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cpf.mask");
      //System.out.println("resource cpf.mask - FOUND!");
    }
    catch (Exception exc)
    {
      //System.out.println("resource cpf.mask - NOT FOUND!");
      mask="###.###.###-##";
    }
    return formatString(result,mask,true);
  }

  /**
   * Formata um numero de CPF com a mascara
   *
   * @param String cpf  - numero do cpf completo
   * @return String
   */
  public static String formatCPF(String cpf)
  {
    if (cpf == null) return "";
    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cpf.mask");
    }
    catch (Exception exc)
    {
      mask="###.###.###-##";
    }
    return formatString(cpf,mask,true);
  }


  /**
   * Parse de numero de CPF
   *
   * @param String strCpf
   * @return Long[] - Long[0] = Numero e Long[1] = D�gito
   */
  public static Long[] parseCPF(String strCpf)
  {
    int ctr = 1;
    Long cpf    = null;
    Long cpfDig = null;
    if (strCpf == null)
      ctr = 0;
    if ((ctr != 0) && (strCpf.length() > 11))
    {
      String result = "";
      String mask = "";
      try
      {
        ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
        mask = resource.getString("cpf.mask");
      }
      catch (Exception exc)
      {
        mask="###.###.###-##";
      }
      result = unformatString(strCpf,mask);
      strCpf = result;
    }

    if ((ctr != 0) && (strCpf.length() == 11))
    {
      cpf    = new Long(strCpf.substring(0,9));
      cpfDig = new Long(strCpf.substring(9,11));
    }
    else
    {
      cpf    = null; //new Long(0);
      cpfDig = null; //new Long(0);
    }
    Long[] arrayCpf = {cpf,cpfDig};
    return arrayCpf;
  }


  /**
   * Formata um numero de CNPJ, apenas preenchendo com zeros a esquerda do
   * numero e do digito.
   *
   * @param Long cnpj    - numero do cnpj
   * @param Long cnpjDig - digito do cnpj
   * @return String
   */
  public static String formatCnpjZerosLeft(Long cnpj, Long cnpjDig)
  {
    if ((cnpj == null) || (cnpjDig == null))
      return "";
    String strCnpj    = "";
    String strCnpjDig = "";
    if (cnpj.toString().length() < 12)
    {
      for(int i=0;i<(12-cnpj.toString().length());i++)
        strCnpj += "0";
    }
    strCnpj = strCnpj+cnpj.toString();
    if (cnpjDig.toString().length() < 2)
    {
      for(int i=0;i<(2-cnpjDig.toString().length());i++)
        strCnpjDig += "0";
    }
    strCnpjDig = strCnpjDig+cnpjDig.toString();
    return strCnpj + strCnpjDig;
  }


  /**
   * Formata um numero de CNPJ com a mascara
   *
   * @param Long cnpj    - numero do cnpj
   * @param Long cnpjDig - digito do cnpj
   * @return String
   */
  public static String formatCNPJ(Long cnpj, Long cnpjDig)
  {
	  
    if ((cnpj == null) || (cnpjDig == null))
      return "";
    String result = formatCnpjZerosLeft(cnpj,cnpjDig);
    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cnpj.mask");
    }
    catch (Exception exc)
    {
      mask="##.###.###/####-##";
    }
    return formatString(result,mask,true);
  }

  /**
   * Formata um numero de CNPJ com a mascara
   *
   * @param String numIni - numero inicial do cnpj
   * @param String numFim - numero final do cnpj
   * @return String
   */
  public static String formatCNPJ(String numIni, String numFim)
  {
    if ((numIni == null) || (numFim == null)) return "";
   
    String result = DataFormatter.fillLeft(DataFormatter.safeString(numIni), 8, '0') +
                    DataFormatter.fillLeft(DataFormatter.safeString(numFim), 5,'0' );

    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cnpj.mask");
      //System.out.println("resource cpf.mask - FOUND!");
    }
    catch (Exception exc)
    {
      //System.out.println("resource cpf.mask - NOT FOUND!");
      mask="##.###.###/####-##";
    }
    return formatString(result,mask,true);
  }

  /**
   * Formata um numero de CNPJ com a mascara
   *
   * @param String cnpj  - numero do cnpj com digito
   * @return String
   */
  public static String formatCNPJ(String cnpj)
  {
    if (cnpj == null) return "";
    String mask = "";
    try
    {
      ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
      mask = resource.getString("cnpj.mask");
    }
    catch (Exception exc)
    {
      mask="##.###.###/####-##";
    }
    return formatString(cnpj,mask,true);
  }

  /**
   * Parse de numero de CNPJ
   *
   * @param String strCnpj
   * @return Long[] - Long[0] = Numero e Long[1] = Digito
   */
  public static Long[] parseCNPJ(String strCnpj)
  {
    int ctr = 1;
    Long cnpj    = null;
    Long cnpjDig = null;
    if (strCnpj == null)
      ctr = 0;
    if ((ctr != 0) && (strCnpj.length() > 14))
    {
      String result = "";
      String mask = "";
      try
      {
        ResourceBundle resource = ResourceBundle.getBundle("ApplicationResources");
        mask = resource.getString("cnpj.mask");
      }
      catch (Exception exc)
      {
        mask="##.###.###/####-##";
      }
      result = unformatString(strCnpj,mask);
      strCnpj = result;
    }

    if ((ctr != 0) && (strCnpj.length() == 14))
    {
      cnpj    = new Long(strCnpj.substring(0,12));
      cnpjDig = new Long(strCnpj.substring(12,14));
    }
    else
    {
      cnpj    = null; //new Long(0);
      cnpjDig = null;//new Long(0);
    }
    Long[] arrayCnpj = {cnpj,cnpjDig};
//    System.out.println(strCnpj);
//    System.out.println(arrayCnpj[0].toString()+" - "+arrayCnpj[1].toString());
    return arrayCnpj;
  }


  /**
   *  Formata uma String a partir de uma mascara,
   *  desde que a mascara siga o padrao
   *  ##.##.##
   *  onde o # identifica os caracteres
   *  N�o funciona se valor.length() > mask.length()
   *
   *  @param String value  - valor a ser formatado
   *  @param String mask   - mascara de formatacao
   *  @param boolean trunc - indica se a deve truncar o
   *                         valor com zeros a esquerda
   */
  public static String formatString(String value, String mask, boolean trunc)
  {
    String aux = "";
    String newMask = "";
    int maskLength = 0;
    String pattern = "";
    String strCtr = "";
    String formatted = null;
    Vector vector = new Vector();
    Object[] arguments = null;
    int elements = 0;
    int j = 0;

    // se algum valor for null
    if ((value == null) || (mask == null))
    {
      return "";
    }

    // EFETUA TRATAMENTO DE TAMANHO NA MASCARA E VALOR
    // verifica o tamanho da mascara; se o usuario escolheu nao truncar,
    // corta a mascara no tamanho do valor
    for (int i=mask.length()-1;i>=0;i--)
    {
      newMask = mask.charAt(i)+newMask;
      if (mask.charAt(i)=='#')
      {
        maskLength++;
      }
      if ((maskLength >=value.length()) && (!trunc))
      {
        break;
      }
    }
    // se o valor for maior que a mascara
    if (value.length() > maskLength)
    {
      return "";
    }
    // Preenche com zeros, se o usuario escolheu truncar
    if ((maskLength > value.length()) && (trunc))
    {
      for(int i=0;i<(maskLength-value.length());i++)
      {
        aux += "0";
//        System.out.println(aux);
      }
      value = aux+value;
    }
    mask = newMask;

    // FORMATA O VALOR DE ACORDO COM A MASCARA

    for (int i=0;i<mask.length();i++)
    {
      if (mask.charAt(i)!='#')
      {
        // quando encontrar um caracter diferente de #
        if (strCtr.length()>0)
        {
          // gera um padrao e o armazena num vector
          pattern = pattern+"{"+String.valueOf(elements)+"}"+mask.charAt(i);
          vector.add(strCtr);
          elements++;
          strCtr = "";
        }
        else
        {
          pattern = pattern+mask.charAt(i);
        }
        continue;
      }
      strCtr = strCtr+value.charAt(j);
      j++;
    }
    pattern = pattern+"{"+String.valueOf(elements)+"}";
    vector.add(strCtr);

    arguments = new Object[vector.size()];
    vector.copyInto(arguments);
    // formata a o valor usando os padr�es gerados
    formatted = MessageFormat.format(pattern, arguments);
    return formatted;
  }


  /**
   * Remove uma mascara de formatacao, desde que a mascara siga o padrao
   *  ##.##.##      onde o # identifica os caracteres
   * Funciona apenas se value.length() == mask.length()
   *
   *  @param String value - valor a ser des-formatado
   *  @param String mask  - mascara de formatacao
   */
  public static String unformatString(String value, String mask)
  {
    //String pattern = "";
   // String strCtr = "";
    String formatted = "";

    // se algum valor for null
    if ((value == null) || (mask == null))
    {
      return "";
    }
    // Mascara e valor devem ter mesmo comprimento
    if ((value.length()) != (mask.length()))
    {
      return "";
    }
    // Remove a mascara e retorna o valor
    for (int i=0;i<mask.length();i++)
    {
      if (mask.charAt(i) == '#')
        formatted = formatted+value.charAt(i);
    }
    return formatted;
  }

  /**
   * Remove uma mascara de formatacao, reescrevendo a string
   * apenas com letras e numeros
   *
   *  @param String value - valor a ser des-formatado
   */
  public static String unformatString(String value)
  {
    String formatted = "";

    // Remove a mascara e retorna o valor
    for (int i = 0; i < value.length(); i++)
    {
      if((value.charAt(i) >= 65 && value.charAt(i) <= 90) ||
         (value.charAt(i) >= 97 && value.charAt(i) <= 122) ||
         (value.charAt(i) >= 48 && value.charAt(i) <= 57))
        formatted = formatted + value.charAt(i);
    }
    return formatted;
  }

  
  public static String safeTrim(String v)
  {
    if (v==null) return "";
    else return v.trim();
  }

  public static Long parseCEP(String prefix, String sufix)  throws ParseException
  {
    long iprefix=0, isufix=0;
    prefix = (prefix!=null?prefix.trim():"");
    sufix = (sufix!=null?sufix.trim():"");

    if (prefix.equals("") || sufix.equals(""))  return null;

    iprefix = Long.parseLong(prefix,10);
    isufix = Long.parseLong(sufix,10);

    long fullCEP = iprefix*1000 + isufix;

    //System.out.println("parseCEP, prefix="+prefix+",sufix="+sufix+",cep="+fullCEP);
    return new Long(fullCEP);
  }


  public static Long parseCEP(String cep)  throws ParseException
  {
  		String cepRet = "";
  		Long retorno = null;
  		if (cep == null || cep.length() == 0) {
  			return null;
  		}
  		StringTokenizer st = new StringTokenizer(cep, "-");
  		if (st.countTokens() != 2) {
  			retorno = new Long(cep);
  		}
  		else {
  			cepRet = st.nextToken();
  			cepRet += st.nextToken();
  			retorno = new Long(cepRet);
  		}
        return retorno;
  }

  
  public static String[] formatCEP(long prefix, long sufix)
  {
    String pref = fillLeft(prefix,5,'0');
    String suf= fillLeft(sufix,3,'0');

    return new String[] {pref, suf};
  }

  public static String formatCEP(long cep)
  {
    String newCep = fillLeft(cep,8,'0');
    return newCep;
  }

  public static String formatCEP(Long cep)
  {
    String newCep = fillLeft(cep.longValue(),8,'0');
    return newCep.substring(0, 5) + "-" + newCep.substring(5);
  }

  public static String formatCNPJZerosLeft(long cnpj)
  {
    String newCnpj = fillLeft(cnpj,14,'0'); 
    return newCnpj;
  }

  public static String formatCPFZerosLeft(long cpf)
  {
    String newCpf = fillLeft(cpf,11,'0'); 
    return newCpf;
  }

  public static String fillLeft(long num, int size, char fillchar) 
  {
   String snum = String.valueOf(num);    
   return fillLeft(snum, size, fillchar) ;
  }
  
  public static String fillLeft(String snum, int size, char fillchar)  
  {
    if (snum.length()>=size) return snum;
    else
    {
      StringBuffer buf = new StringBuffer();
      int dif = size - snum.length();

      for (int x=0; x<dif; x++)
        buf.append(fillchar);

      buf.append(snum);
      return buf.toString();
    }
  }

 public static String fillRight(long num, int size, char fillchar) 
  {
   String snum = String.valueOf(num);    
   return fillRight(snum, size, fillchar) ;
  }
  
  public static String fillRight(String snum, int size, char fillchar)
  {
    if (snum.length()>=size) return snum;
    else
    {
      StringBuffer buf = new StringBuffer();
      int dif = size - snum.length();

      buf.append(snum);

      for (int x=0; x<dif; x++)
        buf.append(fillchar);

      return buf.toString();
    }
  }

  public static String translateString(String arg, String charsToSubstitute, String substituteChars){
    char[] charsFrom = charsToSubstitute.toCharArray();
    char[] charsTo = substituteChars.toCharArray();
    
    for(int i = 0; i < charsFrom.length; i++){
      arg = arg.replace(charsFrom[i], charsTo[i]);
    }
  
    return arg;
  }

  public static String zeroLeft(String origem, int tamanho){
        StringBuffer buffer = new StringBuffer();

        if(origem.length() < tamanho){
          for(int i = 0; i < (tamanho - origem.length()); i++){
            buffer.append('0');
      
          }
          buffer.append(origem);
        }else{
          buffer.append(origem);
        }
        return buffer.toString();
      
  }


  public static String arrayToString(String[] array, String separador){
      StringBuffer buffer = new StringBuffer();

      for(int i = 0; i < array.length; i++) {
          buffer.append(array[i]);
          buffer.append(separador);
      }
      return buffer.substring(0, (buffer.length() - 1));
  }
  /*
   * Formata data abertura de conta (desde) - Aragao
   */
  public static String formatDesde(java.util.Date dataSQL){
	  Date data = new Date(dataSQL.getTime());
	  Locale brasil = new Locale ("pt","BR");
	  DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, brasil);
	  String desde = df.format(data);
	  String desdePos = "";
	  desdePos += desde.substring(3, 5);
	  desdePos += "/";
	  desdePos += desde.substring(6, 10);
	  return desdePos;
  }
  /*
   * Calcula a diferenca entre datas
   * param  Date Data menor data maior
   * return int  Num. dias (Aragao) 
   */
  public static int diferencaEntreData(java.util.Date dtMenor, java.util.Date dtMaior) throws ParseException {
		 GregorianCalendar ini = new GregorianCalendar();
		 GregorianCalendar fim = new GregorianCalendar();
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 ini.setTime(sdf.parse(Formatter.formatDate(dtMenor, "dd/MM/yyyy")));
		 fim.setTime(sdf.parse(Formatter.formatDate(dtMaior, "dd/MM/yyyy")));
       
		 float dt1 = ini.getTime().getTime()/24/60/60/1000;
		 float dt2 = fim.getTime().getTime()/24/60/60/1000;

		 return new Float(dt2 - dt1).intValue();
  }

  public static java.util.Date dataHoje() {
	  GregorianCalendar gre = new GregorianCalendar();
	  java.util.Date hoje = new java.util.Date(gre.getTimeInMillis());
	  return hoje;
  }

  public static java.util.Date dataHojeSomandoDias(int i) {
	  GregorianCalendar gre = new GregorianCalendar();
	  gre.add(GregorianCalendar.DATE, i); // Soma i dias
	  Date data = new Date(gre.getTimeInMillis());
	  return data;
  }

}