package com.users.services;

import com.users.entities.User;

import java.util.List;

public interface UserService {

    User add(User quiz);

    List<User> getAll();

    User getById(Long id);

    void deleteById(Long id);
}
