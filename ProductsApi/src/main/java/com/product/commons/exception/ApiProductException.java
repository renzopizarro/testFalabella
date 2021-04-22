package com.product.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiProductException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426130048041105183L;

	private int code;

	private Object response;

	public ApiProductException(int code) {
		super();
		this.code = code;
		this.response = new Object();
	}

	public ApiProductException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
		this.response = new Object();
	}

	public ApiProductException(String message, int code) {
		super(message);
		this.code = code;
		this.response = new Object();
	}

	public ApiProductException(Throwable cause, int code) {
		super(cause);
		this.code = code;
		this.response = new Object();
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
