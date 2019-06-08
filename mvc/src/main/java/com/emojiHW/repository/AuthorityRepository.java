package com.emojiHW.repository;

import com.emojiHW.domain.Authority;
import com.emojiHW.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    @Query("SELECT a from Authority a join fetch a.user where a.user.id=?1")
    List<Authority> findAuthorityByUserId(Long userId);

    @Query("select a from Authority a join fetch a.user where a.user.id =?1")
    List<Authority> findAuthorities(User domainUser);
}
