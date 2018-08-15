package com.domain.services;

import com.domain.models.Category;
import com.domain.models.SubTask;
import com.domain.models.Task;
import com.domain.repository.SubTaskRepository;
import com.domain.repository.TaskRepository;
import com.sun.javafx.tk.Toolkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Munzir Masri on 11.8.2018.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubTaskRepository subTaskRepository;

    public Task findById(Long id){
        return taskRepository.findOne(id);
    }

    public List<Task> findByCategoryId(Long categoryId){
        return taskRepository.findByCategoryId(categoryId);
    }

     public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task createTask(Long categoryId, Task task) {
        Category myCategoy = categoryService.findById(categoryId);
        task.setCategory(myCategoy);
        return taskRepository.save(task);
    }

    public void markTaskAsCompleted(Long taskId) {
        Task myTask = taskRepository.findOne(taskId);
        myTask.setIsCompleted(true);
        myTask.setCompletionDate(new Date());
         taskRepository.save(myTask);
    }

    public void markTaskAsCompleted(Task myTask) {
        myTask.setIsCompleted(true);
        myTask.setCompletionDate(new Date());
        taskRepository.save(myTask);
    }

    public void markTaskAsNotCompleted(Long taskId){
        Task myTask = taskRepository.findOne(taskId);
        myTask.setIsCompleted(false);
        myTask.setCompletionDate(null);
        taskRepository.save(myTask);
    }

    public void markTaskAsNotCompleted(Task myTask){
        myTask.setIsCompleted(false);
        myTask.setCompletionDate(null);
        taskRepository.save(myTask);
    }


    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void addSubTask(Long taskId, SubTask subTask){
        Task myTask = taskRepository.findOne(taskId);
        myTask.addSubTask(subTask);
        taskRepository.save(myTask);
    }

    public void addSubTask(Task myTask , SubTask subTask){
        //TODO : check if sub entity is being saved .
        myTask.addSubTask(subTask);
        taskRepository.save(myTask);
    }

}
