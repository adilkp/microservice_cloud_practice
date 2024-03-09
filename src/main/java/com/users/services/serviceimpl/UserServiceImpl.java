package com.users.services.serviceimpl;

import com.users.entities.User;
import com.users.exceptions.ResourceNotFoundException;
import com.users.repositories.UserRepository;
import com.users.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PaymentClient paymentClient;

    public UserServiceImpl(UserRepository userRepository, PaymentClient paymentClient) {
        this.userRepository = userRepository;
        this.paymentClient = paymentClient;
    }

    @Override
    public User add(User users) {
        return userRepository.save(users);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        List<User> newUsers = users.stream().map(user -> {
            user.setTransactions(paymentClient.getTransactions(user.getUser_id()));
            return user;
        }).collect(Collectors.toList());
        return newUsers;
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id : " +id+ " not found !!!"));
        user.setTransactions(paymentClient.getTransactions(user.getUser_id()));
        return user;
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id : " +id+ " not found !!!"));
        userRepository.deleteById(user.getUser_id());
    }
}
