package com.fdmgroup.FxTrading.error;

public class UniqueEmailException extends Exception {


	private static final long serialVersionUID = 1L;
	public UniqueEmailException(String errorMessage) {
		super(errorMessage);
	}

}
