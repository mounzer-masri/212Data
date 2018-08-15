package com.domain.repository;

import com.domain.models.WunderUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Munzır Masri on 8.8.2018.
 */
public interface UserRepository extends CrudRepository<WunderUser, Long>{
    @Query("select u from WunderUser u where u.email = :email AND u.password = :password")
    WunderUser findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("select u from WunderUser u where u.email = :email")
    WunderUser findByEmail(@Param("email") String email);
}
