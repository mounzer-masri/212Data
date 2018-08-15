package com.domain.repository;

import com.domain.models.Category;
import com.domain.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Munzir Masri on 11.8.2018.
 */
public interface TaskRepository extends CrudRepository<Task, Long>{

    @Query("select t from Task t where t.category.id = :categoryId")
    List<Task> findByCategoryId(@Param("categoryId") Long categoryId);
}
