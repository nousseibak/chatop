package com.openclassrooms.chatop.repository;

import com.openclassrooms.chatop.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Integer> {

    DbUser findByEmail(String email);
}
