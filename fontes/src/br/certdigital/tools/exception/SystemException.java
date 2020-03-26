package br.certdigital.tools.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Throwable root;
	private Throwable exc;

	public SystemException() {
	}

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(Throwable e) {
		super(e.toString());
		if (getRootCause() == null)
			this.root = e;
		else
			this.exc = e;
	}

	public void printStackTrace() {
		if (root != null)
			root.printStackTrace();
		else if (exc != null)
			exc.printStackTrace();
	}

	public void printStackTrace(PrintStream ps) {
		if (root != null)
			root.printStackTrace(ps);
		else if (exc != null)
			exc.printStackTrace(ps);
	}

	public void printStackTrace(PrintWriter ps) {
		if (root != null)
			root.printStackTrace(ps);
		else if (exc != null)
			exc.printStackTrace(ps);
	}

	public Throwable getRootCause() {
		if (root != null)
			return root;
		else if (exc != null) {
			if (exc instanceof SystemException)
				return ((SystemException) exc).getRootCause();
			else
				return exc;
		} else if (getMessage() != null)
			return new Exception(getMessage());
		else
			return new Exception();
	}
}