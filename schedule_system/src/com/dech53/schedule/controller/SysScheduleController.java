package com.dech53.schedule.controller;

import com.dech53.schedule.common.Result;
import com.dech53.schedule.pojo.SysSchedule;
import com.dech53.schedule.service.SysScheduleService;
import com.dech53.schedule.service.impl.SysScheduleServiceImpl;
import com.dech53.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    private SysScheduleService scheduleService = new SysScheduleServiceImpl();
    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        //  调用服务层方法,查询所有日程
        List<SysSchedule> itemList = scheduleService.findItemListByUid(uid);
        // 将日程信息装入result,转换JSON给客户端
        Map data =new HashMap();
        data.put("itemList",itemList);
        Result result = Result.ok(data);
        WebUtil.writeJson(resp,result);
    }

    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        scheduleService.addDefault(uid);
        WebUtil.writeJson(resp,Result.ok(null));
    }

    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        // 调用服务层方法,更新数据
        scheduleService.updateSchedule(sysSchedule);

        // 响应成功
        WebUtil.writeJson(resp,Result.ok(null));
    }
    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要删除的日程id
        int sid = Integer.parseInt(req.getParameter("sid"));
        // 调用服务层方法,删除日程
        scheduleService.removeSchedule(sid);
        // 响应200
        WebUtil.writeJson(resp,Result.ok(null));
    }
}