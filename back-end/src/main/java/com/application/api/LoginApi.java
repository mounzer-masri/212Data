package com.application.api;

import com.application.dto.UserAccessTokenDto;
import com.application.dto.UserLoginDto;
import com.application.services.LoginService;
import com.domain.models.WunderUser;
import com.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
@CrossOrigin(origins = "https://nkoyjyl9yl.codesandbox.io")
@RestController
public class LoginApi {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public ResponseEntity<UserAccessTokenDto> registerUser(@RequestBody UserLoginDto userLoginDto) {
        try {
            WunderUser logingUser = userService.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());
            if (logingUser == null) {
                return new ResponseEntity<UserAccessTokenDto>(HttpStatus.UNAUTHORIZED);
            } else {
                UserAccessTokenDto userAccessTokenDto = new UserAccessTokenDto(loginService.generateTokenForUser(logingUser));
                return new ResponseEntity<UserAccessTokenDto>(userAccessTokenDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<UserAccessTokenDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
