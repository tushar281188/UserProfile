package com.test.fbcommunication.util;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.fbcommunication.exceptions.BusinessException;
import com.test.fbcommunication.mysql.model.User;

public class FbUserDetailsParser {
	private ObjectMapper objectMapper = new ObjectMapper();

	public User parse(String userDetailsString)
			throws BusinessException {

		if (null == userDetailsString || userDetailsString.equalsIgnoreCase("")) {
			throw new BusinessException("");
		}

		User user = null;
		try {

			JsonNode jsonNode = objectMapper.readTree(userDetailsString);
			user = new User();

			if (!jsonNode.path("id").isMissingNode()) {
				user.setFbid(jsonNode.path("id").asLong());
			}

			if (!jsonNode.path("first_name").isMissingNode()) {
				user.setFirstname(jsonNode.path("first_name").asText());
			}

			if (!jsonNode.path("gender").isMissingNode()) {
				user.setGender(jsonNode.path("gender").asText());
			}

			if (!jsonNode.path("last_name").isMissingNode()) {
				user.setLastname(jsonNode.path("last_name").asText());
			}

			if (!jsonNode.path("link").isMissingNode()) {
				user.setLink(jsonNode.path("link").asText());
			}

			if (!jsonNode.path("locale").isMissingNode()) {
				user.setLocation(jsonNode.path("locale").asText());
			}

			if (!jsonNode.path("name").isMissingNode()) {
				user.setName(jsonNode.path("name").asText());
			}

			if (!jsonNode.path("timezone").isMissingNode()) {
				user.setTimezone(jsonNode.path("timezone").asDouble());
			}

			if (!jsonNode.path("updated_time").isMissingNode()) {
				// TODO: Parse updated_time value to Date object and store
				user.setUpdatedtime(new Date());
			}

			if (!jsonNode.path("verified").isMissingNode()) {
				user.setVerified(jsonNode.path("verified").asBoolean());
			}

		} catch (JsonProcessingException e) {
			throw new BusinessException("");
		} catch (IOException e) {
			throw new BusinessException("");
		}

		return user;
	}
}
