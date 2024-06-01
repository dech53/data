package com.dech53.schedule.controller;

import com.dech53.schedule.common.Result;
import com.dech53.schedule.common.ResultCodeEnum;
import com.dech53.schedule.pojo.SysUser;
import com.dech53.schedule.service.SysUserService;
import com.dech53.schedule.service.impl.SysUserServiceImpl;
import com.dech53.schedule.util.MD5Util;
import com.dech53.schedule.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private SysUserService userService = new SysUserServiceImpl();

    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        SysUser sysUser =  userService.findByUsername(username);
        Result result = Result.ok(null);
        if(null != sysUser){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        SysUser loginUser =  userService.findByUsername(sysUser.getUsername());
        Result result = null;
        if( null == loginUser){
            result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        } else if (!(MD5Util.encrypt(sysUser.getUserPwd())).equals(loginUser.getUserPwd())) {
            result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else {
            Map data = new HashMap();
            loginUser.setUserPwd("");
            data.put("loginUser",loginUser);
            result = Result.ok(data);
        }
        WebUtil.writeJson(resp,result);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser sysUser1 = WebUtil.readJson(req, SysUser.class);
        SysUser sysUser = new SysUser(null, sysUser1.getUsername(), sysUser1.getUserPwd());
        int rows = userService.regist(sysUser);
        Result result = Result.ok(null);
        if (rows<1){
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }
}
