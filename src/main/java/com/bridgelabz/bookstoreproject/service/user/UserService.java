package com.bridgelabz.bookstoreproject.service.user;

import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.entity.User;
import com.bridgelabz.bookstoreproject.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {

        User userById = getUserById(id);
        System.out.println(userDTO.getPassword());
        modelMapper.map(userDTO, userById);
        System.out.println("user password after update completed=====>"+ userById.getPassword());
        userRepository.save(userById);
        return userById;
    }

    @Override
    public User deleteUser(Long id) {
        User userById = getUserById(id);
        userRepository.delete(userById);
        return userById;
    }

}
