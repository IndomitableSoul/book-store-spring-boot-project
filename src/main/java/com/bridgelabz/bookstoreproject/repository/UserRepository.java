package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //provide username and it will return the user of given username
    public User findByUsername(String username);
}
