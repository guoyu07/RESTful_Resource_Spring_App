package com.tanmoy.restcontroller;

public class Error {
	private int code;
	private String messsage;

	public Error(int code, String message) {
		this.code = code;
		this.messsage = message;
	}

	public int getCode() {
		return code;
	}

	public String getMesssage() {
		return messsage;
	}

}
