package com.application.api;

import com.domain.models.WunderUser;
import com.domain.services.UserService;
import com.domain.services.exceptions.UserAlreadyRegisteredException;
import com.domain.services.exceptions.UserMissingInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Munzır Masrı on 9.8.2018.
 */
@CrossOrigin(origins = "https://nkoyjyl9yl.codesandbox.io")
@RestController
public class UserApi {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<WunderUser> registerUser(@RequestBody WunderUser wunderUser,HttpServletResponse response) {
        try {
            System.out.println(response);
            WunderUser createdUser = userService.createUser(wunderUser);
            return new ResponseEntity<WunderUser>(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyRegisteredException e) {
            return new ResponseEntity<WunderUser>(HttpStatus.CONFLICT);
        } catch (UserMissingInfoException e) {
            return new ResponseEntity<WunderUser>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping("/users")
    public ResponseEntity<List<WunderUser>> listUsers() {

        List<WunderUser> wunderUsers = userService.findAllUsers();
        if (wunderUsers.isEmpty()) {
            return new ResponseEntity<List<WunderUser>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<WunderUser>>(wunderUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WunderUser> getUser(@PathVariable("id") long id) {
        WunderUser wunderUser = userService.findById(id);
        if (wunderUser == null) {
            return new ResponseEntity<WunderUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<WunderUser>(wunderUser, HttpStatus.OK);
    }


}
