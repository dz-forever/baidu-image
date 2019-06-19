package com.dzforever.service;

import com.dzforever.dao.UserDao;
import com.dzforever.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(User user) {
        User theUser = userDao.findByName(user.getName());
        if(theUser!=null&&theUser.getPassword().equals(user.getPassword()))
        return theUser;
        else{
            return null;
        }

    }
}
