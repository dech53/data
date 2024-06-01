package com.dech53.schedule.service.impl;

import com.dech53.schedule.dao.SysUserDao;
import com.dech53.schedule.dao.impl.SysUserDaoImpl;
import com.dech53.schedule.pojo.SysUser;
import com.dech53.schedule.service.SysUserService;
import com.dech53.schedule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private SysUserDao UserDao = new SysUserDaoImpl();
    @Override
    public int regist(SysUser registUser) {
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return UserDao.addSysUser(registUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return UserDao.findByUsername(username);
    }
}