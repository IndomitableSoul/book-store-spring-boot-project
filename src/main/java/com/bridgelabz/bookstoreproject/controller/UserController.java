package com.bridgelabz.bookstoreproject.controller;


import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.dto.UserDTO;
import com.bridgelabz.bookstoreproject.entity.LoginUser;
import com.bridgelabz.bookstoreproject.entity.User;
import com.bridgelabz.bookstoreproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    //this api is accessible to authenticated user only
    @RequestMapping("/welcome")
    public String welcome(){
        String text = "This is a private page!!! ";
        text+="Only authorized user can access this page!!!";
        return text;
    }

    //this api also accessible to authenticated users only
    @GetMapping("/getUsers")
    public ResponseEntity<ResponseDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ResponseDTO responseDTO = new ResponseDTO("Got all users Successfully", users);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Long id){
        User userById = userService.getUserById(id);
        ResponseDTO responseDTO = new ResponseDTO("Got user whose id is " + userById.getId(), userById);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long id ,@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User updatedUser = this.userService.updateUser(id, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User with id "+id+" Updated Successfully",updatedUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id ){
        User deletedUser = this.userService.deleteUser(id);
        ResponseDTO responseDTO = new ResponseDTO("User with id "+id+" Deleted Successfully", deletedUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //registration api permitted to be accessed by all the users
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }

    //send OTP
    static Random random = new Random();
    static long otp = random.nextInt(999999);
    String OTP = String.valueOf(otp);
   @PostMapping("/sendOTPToEmail")
    public ResponseEntity<ResponseDTO> sendOTPToEmail(@RequestBody LoginUser loginUser) {
       UserService.sendEmailToUser("OTP: ", OTP);
       ResponseDTO responseDTO = new ResponseDTO("OTP is ", OTP);
       return new ResponseEntity<>(responseDTO, HttpStatus.OK);
   }
}
