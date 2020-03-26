package br.certdigital.tools.util;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Classe que implementa uma paginacao de dados
 *
 */

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Page EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 0, false, 0, 1);

	protected List objects;

	protected int start;

	protected boolean hasNext;

	protected int range;

	protected int total;

	

	/**
	 * Constructor
	 */
	public Page() {}

	/**
	 * Constructor
	 *
	 * @param list Lista para armazenamento de registros
	 * @param start N�mero do primeiro registro
	 * @param hasNext Indicador de existencia da prexima pegina
	 */
	public Page(List list, int start, boolean hasNext, int range, int total) {

		objects = new ArrayList(list);

		this.start = start;
		this.hasNext = hasNext;
		this.range = range;
		this.total = total;

		//System.out.println("PAGE " + getCurrent() + " de " + getTotal());
		//Logger.debug(getClass(), "Page: " + getCurrent() + " de " + getTotal());
	}
	
	/**
	 * Lista de registros
	 * @return List
	 */
	public List getList() {

		return objects;

	}

	/**
	 * Indicador de existencia de uma pagina posterior
	 * @return boolean
	 */
	public boolean isNextPageAvailable() {

		return hasNext;

	}

	/**
	 * Indicador de existancia de uma pagina anterior
	 * @return boolean
	 */
	public boolean isPreviousPageAvailable() {

		return start > 0;

	}

	/**
	 * Namero do primeiro registro da pagina posteior
	 * @return int
	 */
	public int getStartOfNextPage() {

		return start + objects.size(); // +range --> afeta a ultima pagina!

	}

	/**
	 * Namero do altimo registro da pagina anterior
	 * @param int
	 */
	public int getStartOfPreviousPage() {

		return Math.max(start - range, 0); //  objects.size()

	}

	/**
	 * Tamanho da lista
	 * @return int
	 */
	public int size() {
		//return objects.size();
		return getSize();
	}

	public int getSize() {

		return objects.size();

	}

	/**
	 * Returns the range.
	 * @return int
	 */
	public int getRange() {

		return range;

	}

	/**
	 * Returns the start.
	 * @return int
	 */
	public int getStart() {

		return start;

	}

	/**
	 * Sets the range.
	 * @param range The range to set
	 */
	public void setRange(int range) {

		this.range = range;

	}

	/**
	 * Sets the start.
	 * @param start The start to set
	 */
	public void setStart(int start) {

		this.start = start;

	}

	/**
	 * Retorna o namero da pagina corrente
	 * @return int
	 */
	public int getCurrent() {

		return (range == 0 ? 1 : ((int)Math.floor((double)start / (double)range) + 1) );

	}

	/**
	 * Retorna o namero total de paginas
	 * @return int
	 */
	public int getTotal() {

		return (range == 0 ? 1 : (int)Math.ceil((double)total / (double)range) );

	}

	public int getStartOfLastPage() {

		if (range==0) return 1;
		int r = total % range;
		return total - r;  
	}

	public int getTotalItens() {
		return total;

	}

  /**
   * Retorna todas as variaveis no formato "[nome = valor]"
   * @return String
   */
	public String toString() {

		StringBuffer buff = new StringBuffer(500);
		buff.append("\n");
		buff.append("[objects=" + objects + "]\n");
		buff.append("[start="   + start   + "]\n");
		buff.append("[hasNext=" + hasNext + "]\n");
		buff.append("[range="   + range   + "]\n");
		buff.append("[total="   + total   + "]\n");
		buff.append("\n");

		return buff.toString();

	}

}