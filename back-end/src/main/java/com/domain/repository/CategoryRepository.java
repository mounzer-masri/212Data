package com.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.domain.models.*;
import java.util.List;

/**
 * Created by Munzir Masri on 11.8.2018.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("select c from Category c where c.ownerUser.id = :ownerUserId")
    List<Category> findByOwnerUserId(@Param("ownerUserId") Long ownerUserId);
}
