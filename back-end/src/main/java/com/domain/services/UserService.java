package com.domain.services;

import com.domain.models.WunderUser;
import com.domain.repository.UserRepository;
import com.domain.services.exceptions.UserAlreadyRegisteredException;
import com.domain.services.exceptions.UserMissingInfoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Munzır Masri on 8.8.2018.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<WunderUser> findAllUsers() {
        List<WunderUser> wunderUserList = new ArrayList<>();
        userRepository.findAll().forEach(wunderUserList::add);
        return wunderUserList;
    }

    public WunderUser findById(Long id) {
        return userRepository.findOne(id);
    }

    public WunderUser findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public WunderUser createUser(WunderUser wunderUser) throws UserAlreadyRegisteredException, UserMissingInfoException {
        if (!wunderUser.isDataValid()) {
            throw new UserMissingInfoException();
        }

        WunderUser oldUser = userRepository.findByEmail(wunderUser.getEmail());
        if (oldUser != null) {
            throw new UserAlreadyRegisteredException();
        } else {
            return userRepository.save(wunderUser);
        }
    }


}
