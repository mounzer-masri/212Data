package com.application.repository;

import com.application.model.UserAccessToken;
import com.domain.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Munzri Masri on 12.8.2018.
 */
public interface UserAccessTokenRepository  extends CrudRepository<UserAccessToken, String>{
    @Query("select u from UserAccessToken u where u.wunderUser.id = :userId")
    List<UserAccessToken> findByUserId(@Param("userId") Long userId);
}
