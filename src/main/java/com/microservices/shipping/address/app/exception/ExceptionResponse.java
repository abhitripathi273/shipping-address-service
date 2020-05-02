package com.microservices.shipping.address.app.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private String messgae;
	private String details;
	private Date date;
	
	protected ExceptionResponse() {}

	/**
	 * @param messgae
	 * @param details
	 * @param date
	 */
	public ExceptionResponse(String messgae, String details, Date date) {
		super();
		this.messgae = messgae;
		this.details = details;
		this.date = date;
	}

	/**
	 * @return the messgae
	 */
	public String getMessgae() {
		return messgae;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [messgae=" + messgae + ", details=" + details + ", date=" + date + "]";
	}
	
}
