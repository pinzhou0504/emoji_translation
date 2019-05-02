package com.emojiHW.repository;

import com.emojiHW.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    User findByUsernameIgnoreCase(String username);
    List<User> findAll();
//    @Query("select u from User u where u.username = ?1")
//    Optional<User> findUserByUsername(String username);
}
