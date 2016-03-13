package com.test.fbcommunication.http.pojo;

import java.util.Map;

import com.test.fbcommunication.enums.HttpRequestType;

public class CustomizedHttpRequest {
	private String requestUri;
	private HttpRequestType httpRequestType;
	private Map<String, String> requestParameters;
	private Map<String, String> headers;

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public HttpRequestType getHttpRequestType() {
		return httpRequestType;
	}

	public void setHttpRequestType(HttpRequestType httpRequestType) {
		this.httpRequestType = httpRequestType;
	}

	public Map<String, String> getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(Map<String, String> requestParameters) {
		this.requestParameters = requestParameters;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
}
