package com.bridgelabz.bookstoreproject.service.user;

import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.entity.User;

import java.util.List;

public interface IUserService {
    public User addUser(UserDTO user);
    public List<User> getAllUsers();
    User getUserById(Long id);

    User updateUser(Long id, UserDTO userDTO);

    User deleteUser(Long id);
}
