package com.dzforever.dao;

import com.dzforever.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor {
    public User findByName(String name);
}
