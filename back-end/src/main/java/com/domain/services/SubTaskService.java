package com.domain.services;

import com.domain.models.SubTask;
import com.domain.repository.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Munzir Masri on 11.8.2018.
 */
@Service
public class SubTaskService {
    @Autowired
    private SubTaskRepository subTaskRepository;

    public SubTask findById(Long id){
        return subTaskRepository.findOne(id);
    }

    public void delete(Long id){
        subTaskRepository.delete(id);
    }

    public void changeSubTaskStatus(Long subTaskId, Boolean status){
        SubTask mySubTask = subTaskRepository.findOne(subTaskId);
        mySubTask.setIsCompleted(status);
        subTaskRepository.save(mySubTask);
    }
}
