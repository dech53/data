package com.dech53.schedule.dao.impl;

import com.dech53.schedule.dao.BaseDao;
import com.dech53.schedule.dao.SysUserDao;
import com.dech53.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int addSysUser(SysUser registUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return baseUpdate(sql, registUser.getUsername(), registUser.getUserPwd());
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid,username,user_pwd userPwd from sys_user where username = ?";
        List<SysUser> sysUsers = baseQuery(SysUser.class, sql, username);
        return (sysUsers != null && sysUsers.size() > 0) ? sysUsers.get(0) : null;
    }
}
