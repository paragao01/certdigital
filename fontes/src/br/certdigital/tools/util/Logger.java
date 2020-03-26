package br.certdigital.tools.util;

import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe para log
 * 
 
 */
public class Logger {
	public static final String INFO  = "INFO";
	public static final String DEBUG = "DEBUG";
	public static final String WARN  = "WARN";
	public static final String FATAL = "FATAL";
	public static final String TRACE = "TRACE";
	public static final String ERROR = "ERROR";

	/**
	 * Constructor
	 */
	private Logger() {
	}

	/**
	 * Classe auxiliar que obtem o log para determinada classe
	 */
	private synchronized static Log getLog(Class c) {
		return LogFactory.getFactory().getInstance(c);
	}

	/**
	 * Log para informacao
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void info(Class c, Object o) {
		if (o instanceof Throwable) {
			info(c, null, (Throwable)o);
		}
		else {
			info(c, o, null);
		}
	}

	/**
	 * Log para informacao
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void info(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isInfoEnabled()) {
				log.info(o, e);
			}
		}
		else {
			Logger.printToConsole(INFO, c, o, e);
		}
	}

	/**
	 * Log para debug
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void debug(Class c, Object o) {
		if (o instanceof Throwable) {
			debug(c, null, (Throwable)o);
		}
		else {
			debug(c, o, null);
		}
	}

	/**
	 * Log para debug
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void debug(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isDebugEnabled()) {
				log.debug(o, e);
			}
		}
		else {
			Logger.printToConsole(DEBUG, c, o, e);
		}
	}

	/**
	 * Log para envio de alertas
	 * 
	 * @param c Classe corrente que est� utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void warn(Class c, Object o) {
		if (o instanceof Throwable) {
			warn(c, null, (Throwable)o);
		}
		else {
			warn(c, o, null);
		}
	}

	/**
	 * Log para envio de altertas
	 * 
	 * @param c Classe corrente que est� utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void warn(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isWarnEnabled()) {
				log.warn(o, e);
			}
		}
		else {
			Logger.printToConsole(WARN, c, o, e);
		}
	}

	/**
	 * Log para envio de uma excecao critica
	 * 
	 * @param c Classe corrente que est� utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void fatal(Class c, Object o) {
		if (o instanceof Throwable) {
			fatal(c, null, (Throwable)o);
		}
		else {
			fatal(c, o, null);
		}
	}

	/**
	 * Log para envio de uma excecao critica
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void fatal(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isFatalEnabled()) {
				log.fatal(o, e);
			}
		}
		else {
			Logger.printToConsole(FATAL, c, o, e);
		}
	}

	/**
	 * Log para envio de uma excecao 
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void error(Class c, Object o) {
		if (o instanceof Throwable) {
			error(c, null, (Throwable)o);
		}
		else {
			error(c, o, null);
		}
	}

	/**
	 * Log para envio de uma excecao 
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void error(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isErrorEnabled()) {
				log.fatal(o, e);
			}
		}
		else {
			Logger.printToConsole(FATAL, c, o, e);
		}
	}

	/**
	 * Log para rotear 
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 */
	public synchronized static void trace(Class c, Object o) {
		if (o instanceof Throwable) {
			trace(c, null, (Throwable)o);
		}
		else {
			trace(c, o, null);
		}
	}

	/**
	 * Log para rotear
	 * 
	 * @param c Classe corrente que esta utilizando o log
	 * @param o Objeto a ser logado
	 * @param e Excecao
	 */
	public synchronized static void trace(Class c, Object o, Throwable e) {
		Log log = getLog(c);
		if (log != null) {
			if (log.isTraceEnabled()) {
				log.fatal(o, e);
			}
		}
		else {
			Logger.printToConsole(FATAL, c, o, e);
		}
	}

	private synchronized static void printToConsole(String type, Class c, Object o, Throwable e) {
		PrintStream out = System.out;
		if (FATAL.equals(type) || ERROR.equals(type)) { 
			out = System.err;
		}
		out.println("\t+======================================");
		out.println("\t| " + type);
		out.println("\t| Class:     " + c);
		out.println("\t| Object:    " + o);
		out.println("\t| Throwable: " + e);
		out.println("\t+======================================");
	}

	/*
	private synchronized static void printToConsole(String type, Class c, Object o) {
		Logger.printToConsole(type, c, o, null);
	}*/
}