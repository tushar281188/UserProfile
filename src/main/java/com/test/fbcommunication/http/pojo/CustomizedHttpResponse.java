package com.test.fbcommunication.http.pojo;

import com.test.fbcommunication.enums.HttpResponseStatus;

public class CustomizedHttpResponse {
	private String response;
	private HttpResponseStatus responseStatus;
	private String statusMessage;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public HttpResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HttpResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@Override
	public String toString() {
		return "CustomizedHttpResponse [response=" + response
				+ ", responseStatus=" + responseStatus + ", statusMessage="
				+ statusMessage + "]";
	}
}
