package com.springboot.demo.repository;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.view.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    boolean findByEmailAndStatus(String email, int i);
    UserEntity findByEmailAndStatusNot(String email, int i);
    Collection<UserEntity> findByStatus(int i);
}
