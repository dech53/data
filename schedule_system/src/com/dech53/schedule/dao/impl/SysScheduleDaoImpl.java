package com.dech53.schedule.dao.impl;

import com.dech53.schedule.dao.BaseDao;
import com.dech53.schedule.dao.SysScheduleDao;
import com.dech53.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public int addSchedule(SysSchedule schedule) {
        String sql = "insert into sys_schedule values(DEFAULT,?,?,?)";
        int rows = baseUpdate(sql,schedule.getUid(),schedule.getTitle(),schedule.getCompleted());
        return rows;
    }

    @Override
    public List<SysSchedule> findAll() {
        String sql = "select sid,uid,title,completed from sys_schedule";
        List<SysSchedule> sysScheduleList = baseQuery(SysSchedule.class, sql);
        return sysScheduleList;
    }

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        String sql ="select sid,uid,title,completed from sys_schedule where uid = ? ";
        return baseQuery(SysSchedule.class,sql,uid);
    }

    @Override
    public Integer addDefault(int uid) {
        String sql = "insert into sys_schedule value(default,?,'请输入日程',0)";
        return baseUpdate(sql,uid);
    }
    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        String sql ="update sys_schedule set title = ? ,completed =  ? where sid =?";
        return baseUpdate(sql,sysSchedule.getTitle(),sysSchedule.getCompleted(),sysSchedule.getSid());
    }
    @Override
    public Integer removeBySid(int sid) {
        String sql ="delete from sys_schedule where sid = ?";

        return baseUpdate(sql,sid);
    }
}