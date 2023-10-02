package com.chat.cc_mini_project.services;

import com.chat.cc_mini_project.exceptions.UserAlreadyExistException;
import com.chat.cc_mini_project.exceptions.UserNotFoundException;
import com.chat.cc_mini_project.model.User;

import java.util.List;

public interface UserService {
    List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}
