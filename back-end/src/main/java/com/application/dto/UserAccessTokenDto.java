package com.application.dto;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
public class UserAccessTokenDto {
    private String token;

    public UserAccessTokenDto(String token){
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
