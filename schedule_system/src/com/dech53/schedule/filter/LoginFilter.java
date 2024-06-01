package com.dech53.schedule.filter;

import com.dech53.schedule.pojo.SysUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.LogRecord;
//@WebFilter(urlPatterns = {"/showSchedule.html","/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        if(null == sysUser){
            response.sendRedirect("/login.html");
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
