package com.dech53.schedule.dao;

import com.dech53.schedule.pojo.SysUser;

public interface SysUserDao {
    int addSysUser(SysUser registUser);
    SysUser findByUsername(String username);
}
