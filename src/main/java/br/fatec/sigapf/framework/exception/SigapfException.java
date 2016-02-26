package br.fatec.sigapf.framework.exception;

public class SigapfException extends Exception {

	private static final long serialVersionUID = -688459126850643800L;

	public SigapfException(String string) {
		super(string);
	}

	public SigapfException() {
		super();
	}
}
