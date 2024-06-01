package com.dech53.schedule.service;

import com.dech53.schedule.pojo.SysUser;


public interface SysUserService {
    int regist(SysUser registUser);
    SysUser findByUsername(String username);

}
