package com.test.fbcommunication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.fbcommunication.application.constants.ApplicationConstants;
import com.test.fbcommunication.application.constants.ApplicationKeyConstants;
import com.test.fbcommunication.enums.HttpResponseStatus;
import com.test.fbcommunication.exceptions.BusinessException;
import com.test.fbcommunication.http.connector.HttpClient;
import com.test.fbcommunication.http.pojo.CustomizedHttpRequest;
import com.test.fbcommunication.http.pojo.CustomizedHttpResponse;
import com.test.fbcommunication.mysql.dao.impl.UserDaoImpl;
import com.test.fbcommunication.mysql.model.User;
import com.test.fbcommunication.util.FbUserDetailsParser;

@Controller
public class FacebookApiCommunicator {

	private HttpClient httpClient = new HttpClient();
	private FbUserDetailsParser fbUserDetailsParser = new FbUserDetailsParser();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	@RequestMapping(value = "/fbDetails", method = RequestMethod.GET)
	public String fetchAndSave(
			@RequestParam("access_token") String access_token, Model model)
			throws BusinessException {

		CustomizedHttpRequest customizedHttpRequest = new CustomizedHttpRequest();
		String requestUri = ApplicationConstants.FB_USER_DETAILS_URI;

		if (null == access_token || access_token.equals("")) {
			model.addAttribute("errorMessage",
					"INVALID access_token parameter value received");
			return "error";
		}

		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams
				.put(ApplicationKeyConstants.FB_ACCESS_TOKEN, access_token);

		customizedHttpRequest.setRequestUri(requestUri);
		customizedHttpRequest.setRequestParameters(requestParams);

		CustomizedHttpResponse customizedHttpResponse = null;
		try {
			customizedHttpResponse = httpClient.doGET(customizedHttpRequest);
		} catch (BusinessException e) {
			model.addAttribute("errorMessage",
					"Exception while communication with Facebook getUserGraphDetails() API");
			return "error";
		}

		if (customizedHttpResponse.getResponseStatus() == HttpResponseStatus.FAILURE) {
			model.addAttribute("errorMessage", "Invalid respose received");
			return "error";
		}

		User user = fbUserDetailsParser.parse(customizedHttpResponse
				.getResponse());

		if (!userDaoImpl.saveOrUpdate(user)) {
			model.addAttribute("errorMessage",
					"Exception while saving user information to database");
			return "error";
		}

		model.addAttribute("UserName", user.getName());
		model.addAttribute("UserFbId", user.getFbid());
		model.addAttribute("UserProfileLink", user.getLink());

		return "fbUserDetails";
	}
}