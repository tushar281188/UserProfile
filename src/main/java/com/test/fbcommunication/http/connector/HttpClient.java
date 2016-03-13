package com.test.fbcommunication.http.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.test.fbcommunication.enums.HttpResponseStatus;
import com.test.fbcommunication.exceptions.BusinessException;
import com.test.fbcommunication.http.pojo.CustomizedHttpRequest;
import com.test.fbcommunication.http.pojo.CustomizedHttpResponse;

public class HttpClient {

	public CustomizedHttpResponse doGET(CustomizedHttpRequest httpRequest)
			throws BusinessException {

		if (null == httpRequest.getRequestUri()
				|| httpRequest.getRequestUri().equals("")) {
			throw new BusinessException("Invalid request uri received");
		}

		HttpGet httpGet = new HttpGet(httpRequest.getRequestUri());
		if (null != httpRequest.getHeaders()
				&& !httpRequest.getHeaders().isEmpty()) {
			for (Map.Entry<String, String> entry : httpRequest.getHeaders()
					.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}

		try {
			URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
			if (null != httpRequest.getRequestParameters()
					&& !httpRequest.getRequestParameters().isEmpty()) {
				for (Map.Entry<String, String> entry : httpRequest
						.getRequestParameters().entrySet()) {

					uriBuilder.addParameter(entry.getKey(), entry.getValue());
				}
			}

			URI finalUri = uriBuilder.build();
			httpGet.setURI(finalUri);

		} catch (URISyntaxException e) {
			throw new BusinessException(
					"Exception while performing URI build operation");
		}

		CustomizedHttpResponse customizedHttpResponse = new CustomizedHttpResponse();
		customizedHttpResponse.setResponseStatus(HttpResponseStatus.FAILURE);

		CloseableHttpClient httpClient = null;
		BufferedReader reader = null;
		try {
			httpClient = HttpClients.createDefault();
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

			reader = new BufferedReader(new InputStreamReader(httpResponse
					.getEntity().getContent()));

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}

			if (200 == httpResponse.getStatusLine().getStatusCode()) {
				customizedHttpResponse
						.setResponseStatus(HttpResponseStatus.SUCCESS);
			}

			customizedHttpResponse.setResponse(response.toString());

		} catch (Exception e) {
			throw new BusinessException("");

		} finally {
			try {
				if (null != httpClient) {
					httpClient.close();
				}

				if (null != reader) {
					reader.close();
				}

			} catch (IOException e) {
				throw new BusinessException("");
			}
		}

		return customizedHttpResponse;
	}
}