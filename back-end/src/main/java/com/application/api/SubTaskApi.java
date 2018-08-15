package com.application.api;

import com.application.services.LoginService;
import com.application.services.exceptions.AccessTokenException;
import com.domain.models.SubTask;
import com.domain.models.Task;
import com.domain.models.WunderUser;
import com.domain.services.CategoryService;
import com.domain.services.SubTaskService;
import com.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Munzri Masri on 12.8.2018.
 */
@CrossOrigin(origins = "https://nkoyjyl9yl.codesandbox.io")
@RestController
public class SubTaskApi {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubTaskService subTaskService;

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/tasks/{id}/subTasks/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask(@PathVariable("id") long taskId, @RequestBody SubTask subTask, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        Task task = taskService.findById(taskId);

        if(task == null || subTask == null || subTask.getId() != null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        //validate user with CategoryId .
        if(!task.getCategory().getOwnerUser().getId().equals(logingUser.getId())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        taskService.addSubTask(task, subTask);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subtasks/{id}/markSubTaskAsCompleted", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity markSubTaskAsCompleted(@PathVariable("id") long subTaskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }



        subTaskService.changeSubTaskStatus(subTaskId, true);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/subtasks/{id}/markSubTaskAsNotCompleted", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity markSubTaskAsNotCompleted(@PathVariable("id") long subTaskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        subTaskService.changeSubTaskStatus(subTaskId, false);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/subtasks/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSubTask(@PathVariable("id") long subTaskId, @RequestHeader("access-token") String accessToken) {
        //validate token .
        WunderUser logingUser = null;
        try {
            logingUser = loginService.validateToken(accessToken);
        } catch (AccessTokenException e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        subTaskService.delete(subTaskId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
