package com.application.api;

import com.application.services.LoginService;
import com.application.services.exceptions.AccessTokenException;
import com.domain.models.Category;
import com.domain.models.Task;
import com.domain.models.WunderUser;
import com.domain.services.CategoryService;
import com.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Munzir Masri on 12.8.2018.
 */
@CrossOrigin(origins = "https://nkoyjyl9yl.codesandbox.io")
@RestController
public class TaskApi {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/categories/{id}/tasks/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@PathVariable("id") long categoryId, @RequestBody Task task, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity<Task>(HttpStatus.UNAUTHORIZED);
        }


        if (task.getId() != null) {
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        }

        //validate category
        Category myCategory = categoryService.findById(categoryId);

        if (myCategory == null) {
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myCategory.getOwnerUser().getId().equals(logingUser.getId())) {
            return new ResponseEntity<Task>(HttpStatus.BAD_REQUEST);
        }
        task.setCategory(myCategory);
        task = taskService.createTask(task);
        return new ResponseEntity<Task>(task, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/categories/{id}/tasks/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> findTasksByCategory(@PathVariable("id") long categoryId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity<List<Task>>(HttpStatus.UNAUTHORIZED);
        }

        //validate category
        Category myCategory = categoryService.findById(categoryId);

        if (myCategory == null) {
            return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myCategory.getOwnerUser().getId().equals(logingUser.getId())) {
            return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);
        }

        List<Task> tasks = taskService.findByCategoryId(categoryId);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{taskId}/markTaskAsCompleted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity markTaskAsCompleted(@PathVariable("taskId") long taskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Task myTask = taskService.findById(taskId);
        if (myTask == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myTask.getCategory().getId().equals(logingUser.getId())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        taskService.markTaskAsCompleted(myTask);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{taskId}/markTaskAsNotCompleted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity markTaskAsNotCompleted(@PathVariable("taskId") long taskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Task myTask = taskService.findById(taskId);
        if (myTask == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myTask.getCategory().getId().equals(logingUser.getId())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        taskService.markTaskAsNotCompleted(myTask);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteTask(@PathVariable("taskId") long taskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Task myTask = taskService.findById(taskId);
        if (myTask == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myTask.getCategory().getId().equals(logingUser.getId())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        taskService.deleteTask(myTask);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateTask(@RequestBody Task myTask, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        if (myTask == null || myTask.getId() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //validate user with CategoryId .
        if (!myTask.getCategory().getId().equals(logingUser.getId())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        taskService.updateTask(myTask);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
