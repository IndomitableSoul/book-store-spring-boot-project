package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.entity.CustomUserDetails;
import com.bridgelabz.bookstoreproject.entity.User;
import com.bridgelabz.bookstoreproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User not Found!!");
        }
        else{
            return new CustomUserDetails(user);
        }


//       if(username.equals("Nikita")){
//           return new User("Nikita", "Nikita@123", new ArrayList<>());
//       }else {
//           throw new UsernameNotFoundException("User not found!!!");
//       }
    }
}
