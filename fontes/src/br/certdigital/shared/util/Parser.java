package br.certdigital.shared.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;

import br.certdigital.shared.exception.ParserException;

public class Parser {

    /**
     * Realiza um parser para o tipo Long
     * <br>
     * Foi utilizado o tipo Object para que nao haja necessidade de
     * realizar o "casting" sempre que o metodo for chamado.
     *
     * @return Integer
     * @exception ParserException Uma excecao sera gerada se ocorrer
     * um erro:
     * <br> - o parametro nao for subclasse de java.lang.Number
     */
    public static Integer parseInteger(Object o) throws ParserException {
        if ( (o != null) && !(o instanceof Number) ) {
            throw new ParserException("Tipo de dado invalido em Parser.parseInteger");
        }
        return (Integer)parseNumber((Number)o, Types.INTEGER);
    }

    /**
     * Realiza um parser para o tipo Long
     * <br>
     * Foi utilizado o tipo Object para que nao haja necessidade de
     * realizar o "casting" sempre que o metodo for chamado.
     *
     * @return Long
     * @exception ParserException Uma excecao gerada se ocorrer um
     * erro:
     * <br> - se o parametro nao for subclasse de java.lang.Number
     */
    public static Long parseLong(Object o) throws ParserException {
        if ( (o != null) && !(o instanceof Number) ) {
            throw new ParserException("Tipo de dado invalido em Parser.parseLong");
        }
        return (Long)parseNumber((Number)o, Types.BIGINT);
    }

    /**
     * Realiza um parser para o tipo Float
     * <br>
     * Foi utilizado o tipo Object para que nao haja necessidade de
     * realizar o "casting" sempre que o metodo for chamado.
     *
     * @return Float
     * @exception ParserException Excecao gerada se <br>
     * - o parametro nao for subclasse de java.lang.Number.
     */
    public static Float parseFloat(Object o) throws ParserException {
        if ( (o != null) && !(o instanceof Number) ) {
            throw new ParserException("Tipo de dado invalido em Parser.parseLong");
        }
        return (Float)parseNumber((Number)o, Types.FLOAT);
    }

    /**
     * Realiza um parser para o tipo Double
     * <br>
     * Foi utilizado o tipo Object para que nwo haja necessidade de
     * realizar o "casting" sempre que o metodo for chamado.
     *
     * @return Double
     * @exception ParserException Excecao gerada se:
     * <br> -  o parametro nao for subclasse de java.lang.Number.
     */
    public static Double parseDouble(Object o) throws ParserException {
        if ( (o != null) && !(o instanceof Number) ) {
            throw new ParserException("Tipo de dado invalido em Parser.parseDouble");
        }
        return (Double)parseNumber((Number)o, Types.DOUBLE);
    }

    /**
     * E um metodo auxiliar que realiza um parser para
     * determinado tipo.
     *
     * @param n Number
     * @param type Tipo para o qual devera ser convertido
     * @return Number
     */
    private static Number parseNumber(Number n, int type) {
        BigDecimal num = (BigDecimal)n;
        if (num == null) {
            return null;
        }
        switch(type) {
            case Types.BIGINT: return new Long(num.longValue());
            case Types.INTEGER: return new Long(num.intValue());
            case Types.DOUBLE: return new Double(num.doubleValue());
            case Types.FLOAT: return new Float(num.floatValue());
            default: return null;
        }
    }

    /**
     *  Faz o parse de Timestamp para java.util.Date
     *
     */
    public static java.util.Date parseDate(Object o) throws ParserException {
        if (o == null) {
            return null;
        }
        if ( (o != null) && !(o instanceof Timestamp) ) {
            throw new ParserException("Tipo de dado invalido em Parser.Timestamp");
        }
        return new java.util.Date(((Timestamp) o).getTime());
    }
}
