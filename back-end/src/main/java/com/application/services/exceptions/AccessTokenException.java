package com.application.services.exceptions;


import com.application.model.UserAccessToken;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
public class AccessTokenException extends RuntimeException {

	public AccessTokenException(UserAccessToken accessToken) {
		super(accessToken == null ? "Access token should not be null!" :
				"Access token is expired: " + accessToken.getToken());
	}
}
