package com.application.services;

import com.application.model.UserAccessToken;
import com.application.repository.UserAccessTokenRepository;
import com.application.services.exceptions.AccessTokenException;
import com.domain.models.WunderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
@Service
public class LoginService {
    @Autowired
    private UserAccessTokenRepository userAccessTokenRepository;

    public WunderUser validateToken(String token){
        if(token == null){
            throw new AccessTokenException(null);
        }
        UserAccessToken accessToken = userAccessTokenRepository.findOne(token);
        if (accessToken == null || accessToken.isExpired()) throw new AccessTokenException(accessToken);
        return accessToken.getWunderUser();
    }

    public String generateTokenForUser(WunderUser wunderUser){
        List<UserAccessToken> userAccessTokenList = userAccessTokenRepository.findByUserId(wunderUser.getId());
        userAccessTokenList.forEach(userAccessTokenRepository::delete);
        return userAccessTokenRepository.save(new UserAccessToken(wunderUser)).getToken();
    }
}
