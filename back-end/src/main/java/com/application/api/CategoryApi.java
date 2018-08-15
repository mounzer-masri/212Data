package com.application.api;

import com.application.dto.CategoryDto;
import com.application.services.LoginService;
import com.application.services.exceptions.AccessTokenException;
import com.domain.models.Category;
import com.domain.models.WunderUser;
import com.domain.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
@CrossOrigin(origins = "https://nkoyjyl9yl.codesandbox.io")
@RestController
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/categories/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("access-token") String accessToken) {
        //getWunderUser && validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity<Category>(HttpStatus.UNAUTHORIZED);
        }
        Category myCategory = new Category();
        myCategory.setName(categoryDto.getName());
        myCategory.setOwnerUser(logingUser);
        myCategory = categoryService.createCategory(myCategory);
        return new ResponseEntity<Category>(myCategory , HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDto>> getUser(@RequestHeader("access-token") String accessToken) {
        //getWunderUser && validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity<List<CategoryDto>>(HttpStatus.UNAUTHORIZED);
        }
        List<Category> categories = categoryService.findByOwnerUserId(logingUser.getId());
        List<CategoryDto> categoriesResponse = new ArrayList<>();
        categories.forEach(c -> { categoriesResponse.add(new CategoryDto(c.getId(), c.getName())); });

        return new ResponseEntity<List<CategoryDto>>(categoriesResponse , HttpStatus.OK);
    }


}

