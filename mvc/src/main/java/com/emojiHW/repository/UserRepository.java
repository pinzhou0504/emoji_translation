package com.emojiHW.repository;

import com.emojiHW.domain.Authority;
import com.emojiHW.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
//    User findByEmailIgnoreCase(String email);

    User findByUsernameIgnoreCase(String username);

    @Query("select u from User u left join fetch u.conversations where u.username=?1")
    User findByUsernameIgnoreCaseWithConversation(String username);

    @Query("select u from User u left join fetch u.conversations ")
    List<User> findAll();

    @Query("select u from User u  left join fetch u.conversations where u.id=?1")
    Optional<User> findById(Long id);

    @Query("select u from User u where u.phoneNumber=?1")
    List<User> findByPhoneNumber(String phoneNumber);


//    @Query("select u from User u where u.username = ?1")
//    Optional<User> findUserByUsername(String username);
}
