package com.xiaoy.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.User;
import com.xiaoy.user.dao.UserDao;

@Repository
public class UserDaoImpl extends CommonImpl<User> implements UserDao
{

}
