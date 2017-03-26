package com.service;

import com.entity.newT.UserT;
import com.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by zengqin on 2017/3/25.
 */
@Service
public class UserService {
    public UserT userToUserT(User user){
        UserT userT=new UserT(user.getOpenId(),user.getUserName());
        return userT;

    }


}
